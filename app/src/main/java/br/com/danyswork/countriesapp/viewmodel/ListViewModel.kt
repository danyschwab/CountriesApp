package br.com.danyswork.countriesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.danyswork.countriesapp.model.CountryModel

class ListViewModel : ViewModel() {

    val countries: MutableLiveData<List<CountryModel>> = MutableLiveData()
    val countryLoadError: MutableLiveData<Boolean> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()

    fun refresh(){
        fetchCountries()
    }

    private fun fetchCountries(){
        val country1 = CountryModel("Albania", "Tirana", "")
        val country2 = CountryModel("Brazil", "Brasilia", "")
        val country3 = CountryModel("Czechia", "Praha", "")

        val list = ArrayList<CountryModel>()
        list.add(country1)
        list.add(country2)
        list.add(country3)

        countries.value = list
        countryLoadError.value = false
        loading.value = false
    }
}