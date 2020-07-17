package br.com.danyswork.countriesapp.model

import br.com.danyswork.countriesapp.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject


class CountriesService @Inject constructor(private val api: CountriesApi) {

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCountries(): Single<MutableList<CountryModel>> {
        return api.getCountries()
    }
}
