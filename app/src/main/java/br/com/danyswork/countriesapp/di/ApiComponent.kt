package br.com.danyswork.countriesapp.di

import br.com.danyswork.countriesapp.model.CountriesService
import br.com.danyswork.countriesapp.viewmodel.ListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CountriesService)
    fun inject(viewModel: ListViewModel)
}
