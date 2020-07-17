package br.com.danyswork.countriesapp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.danyswork.countriesapp.R
import br.com.danyswork.countriesapp.model.CountryModel
import br.com.danyswork.countriesapp.viewmodel.ListViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: ListViewModel

    private lateinit var adapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        countriesList.layoutManager = LinearLayoutManager(this)
        countriesList.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
            swipeRefreshLayout.isRefreshing = false
        }

        observerViewModel();
    }

    private fun observerViewModel() {
        viewModel.countries.observe(this,
            Observer<MutableList<CountryModel>> { countryModels: MutableList<CountryModel> ->
                if (countryModels != null) {
                    countriesList.visibility = View.VISIBLE
                    adapter.updateCountries(countryModels)
                }
            }
        )
        viewModel.countryLoadError.observe(this,
            Observer { isError: Boolean? ->
                if (isError != null) {
                    listError.visibility = if (isError) View.VISIBLE else View.GONE
                }
            }
        )
        viewModel.loading.observe(
            this,
            Observer { isLoading: Boolean? ->
                if (isLoading != null) {
                    loadingView.visibility = if (isLoading) View.VISIBLE else View.GONE
                    if (isLoading) {
                        listError.visibility = View.GONE
                        countriesList.visibility = View.GONE
                    }
                }
            }
        )
    }

}
