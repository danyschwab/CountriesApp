package br.com.danyswork.countriesapp.di

import br.com.danyswork.countriesapp.model.CountriesService
import br.com.danyswork.countriesapp.model.CountriesServiceImpl
import dagger.Binds
import dagger.Module


@Module
abstract class ApiModule {

    @Binds
    abstract fun bindCountriesService(impl: CountriesServiceImpl): CountriesService
}
