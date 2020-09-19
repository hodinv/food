package com.hodinv.products.interactors

import com.hodinv.products.TestSchedulerRule
import com.hodinv.products.model.ProductType
import com.hodinv.products.services.network.DataApi
import com.hodinv.products.services.repository.ProductsRepository
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class PrepareDataInteractorImplTest {

    private lateinit var interactor: PrepareDataInteractor
    private lateinit var api: DataApi
    private lateinit var repository: ProductsRepository

    @Rule
    @JvmField
    val rule = TestSchedulerRule()

    @Before
    fun prepare() {
        api = Mockito.mock(DataApi::class.java)
        repository = Mockito.mock(ProductsRepository::class.java)
        interactor = PrepareDataInteractorImpl(
            api, repository
        )
    }


    @Test
    fun testCheckNotReady() {
        val list: List<ProductType> = emptyList()

        Mockito.`when`(repository.isReady()).thenReturn(Single.just(false))
        Mockito.`when`(api.getData()).thenReturn(Single.just(list))
        Mockito.`when`(repository.setList(list)).thenReturn(Completable.complete())

        val test = interactor.prepare().test()
        rule.testScheduler.triggerActions()
        test.assertComplete()

        Mockito.verify(repository, Mockito.times(1)).isReady()
        Mockito.verify(api, Mockito.times(1)).getData()
        Mockito.verify(repository, Mockito.times(1)).setList(list)
        Mockito.verifyNoMoreInteractions(repository)
        Mockito.verifyNoMoreInteractions(api)
    }

    @Test
    fun testCheckReady() {
        Mockito.`when`(repository.isReady()).thenReturn(Single.just(true))

        val test = interactor.prepare().test()
        rule.testScheduler.triggerActions()
        test.assertComplete()

        Mockito.verify(repository, Mockito.times(1)).isReady()
        Mockito.verifyNoMoreInteractions(repository)
        Mockito.verifyNoMoreInteractions(api)

    }
}