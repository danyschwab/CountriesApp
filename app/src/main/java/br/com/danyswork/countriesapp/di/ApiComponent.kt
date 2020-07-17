package br.com.danyswork.countriesapp.di

import br.com.danyswork.countriesapp.model.CountriesService
import br.com.danyswork.countriesapp.viewmodel.ListViewModel
import dagger.Component


@Component(modules = [ApiModule::class])
abstract class ApiComponent {

    abstract fun inject(service: CountriesService)

    abstract fun inject(viewModel: ListViewModel)
}
