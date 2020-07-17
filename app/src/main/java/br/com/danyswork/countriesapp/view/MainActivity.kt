package br.com.danyswork.countriesapp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.danyswork.countriesapp.R
import br.com.danyswork.countriesapp.model.CountryModel
import br.com.danyswork.countriesapp.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: ListViewModel

    private var adapter: CountryAdapter = CountryAdapter(this, mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        countriesList.layoutManager = LinearLayoutManager(this)
        countriesList.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
            swipeRefreshLayout.isRefreshing = false
        }

        observerViewModel()
        viewModel.refresh()

    }

    private fun observerViewModel() {
        viewModel.countries.observe(this,
            Observer<MutableList<CountryModel>> { countryModels: MutableList<CountryModel> ->
                countriesList.visibility = View.VISIBLE
                adapter.updateCountries(countryModels)
            }
        )
        viewModel.countryLoadError.observe(this,
            Observer { isError: Boolean ->
                verifyError(isError)
            }
        )
        viewModel.loading.observe(
            this,
            Observer { isLoading: Boolean ->
                verifyLoading(isLoading)
            }
        )
    }

    private fun verifyLoading(isLoading: Boolean) {
        if (isLoading) {
            showLoading()
        } else {
            hideLoading()
        }
    }

    private fun verifyError(isError: Boolean) {
        if (isError) {
            showError()
        } else {
            hideError()
        }
    }

    private fun showError() {
        listError.visibility = View.VISIBLE
        countriesList.visibility = View.GONE
        loadingView.visibility = View.GONE
    }

    private fun hideError() {
        listError.visibility = View.GONE
    }

    private fun hideLoading() {
        loadingView.visibility = View.GONE
    }

    private fun showLoading() {
        listError.visibility = View.GONE
        countriesList.visibility = View.GONE
        loadingView.visibility = View.VISIBLE
    }
}
