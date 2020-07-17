package br.com.danyswork.countriesapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.danyswork.countriesapp.model.CountriesService
import br.com.danyswork.countriesapp.model.CountryModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler.ExecutorWorker
import io.reactivex.plugins.RxJavaPlugins
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.concurrent.Callable
import java.util.concurrent.Executor

@RunWith(JUnit4::class)
class ListViewModelTest {


    companion object {
        @ClassRule @JvmField
        val rule = InstantTaskExecutorRule()
    }

    @Mock
    private val countriesService: CountriesService = mock()

    @InjectMocks
    private var listViewModel = ListViewModel()

    @Before
    fun setupRxSchedulers() {
        MockitoAnnotations.initMocks(this)
        val immediate: Scheduler = object : Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorWorker(
                    Executor { runnable: Runnable -> runnable.run() },
                    true
                )
            }
        }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { _: Callable<Scheduler> -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _: Callable<Scheduler> -> immediate }
    }
    @Test
    fun getCountriesSuccess() {
        val country = CountryModel("countryName", "capital", "flag")
        val countriesList: MutableList<CountryModel> = mutableListOf(country)

        whenever(countriesService.getCountries()).thenReturn(Single.just(countriesList))

        listViewModel.refresh()

        assertEquals(1, listViewModel.countries.value!!.size)
        assertEquals(false, listViewModel.countryLoadError.value)
        assertEquals(false, listViewModel.loading.value)
    }

    @Test
    fun getCountriesFail() {
        whenever(countriesService.getCountries()).thenReturn(Single.error(Throwable()))

        listViewModel.refresh()

        assertEquals(true, listViewModel.countryLoadError.value)
        assertEquals(false, listViewModel.loading.value)
    }



}