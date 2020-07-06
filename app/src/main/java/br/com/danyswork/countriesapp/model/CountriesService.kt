package br.com.danyswork.countriesapp.model

import io.reactivex.Single
import javax.inject.Inject


interface CountriesService {
    fun getCountries(): Single<MutableList<CountryModel>>
}

class CountriesServiceImpl @Inject constructor(
    private val api: CountriesApi
) : CountriesService {

    override fun getCountries(): Single<MutableList<CountryModel>> {
        return api.getCountries()
    }
}
