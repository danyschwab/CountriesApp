package br.com.danyswork.countriesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.danyswork.countriesapp.di.DaggerApiComponent
import br.com.danyswork.countriesapp.model.CountriesService
import br.com.danyswork.countriesapp.model.CountryModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class ListViewModel : ViewModel() {

    init {
        DaggerApiComponent.create().inject(this)
    }

    val countries: MutableLiveData<MutableList<CountryModel>> = MutableLiveData()
    val countryLoadError: MutableLiveData<Boolean> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()

    private val disposable = CompositeDisposable()

    @Inject
    lateinit var countriesService: CountriesService

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true
        disposable.add(
            countriesService.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    DisposableSingleObserver<MutableList<CountryModel>>() {
                    override fun onSuccess(countryModels: MutableList<CountryModel>) {
                        countries.value = countryModels
                        countryLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        countryLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}