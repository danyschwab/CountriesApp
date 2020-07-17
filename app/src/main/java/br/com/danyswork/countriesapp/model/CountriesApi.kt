package br.com.danyswork.countriesapp.model

import io.reactivex.Single
import retrofit2.http.GET


interface CountriesApi {

    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com"
    }


    @GET("${BASE_URL}/DevTides/countries/master/countriesV2.json")
    fun getCountries(): Single<MutableList<CountryModel>>
}