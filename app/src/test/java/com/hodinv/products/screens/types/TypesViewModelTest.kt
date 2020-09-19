package com.hodinv.products.screens.types

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hodinv.products.TestSchedulerRule
import com.hodinv.products.interactors.ProductsListInteractor
import com.hodinv.products.model.ListItem
import io.reactivex.Single
import io.reactivex.plugins.RxJavaPlugins
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito

class TypesViewModelTest {

    private lateinit var viewModel: TypesViewModel
    private lateinit var router: TypesRouter
    private lateinit var interactor: ProductsListInteractor

    @Rule
    @JvmField
    val rule = TestSchedulerRule()

    @Rule
    @JvmField
    val ruleInstantRun: TestRule = InstantTaskExecutorRule()

    @Before
    fun prepare() {
        router = Mockito.mock(TypesRouter::class.java)
        interactor = Mockito.mock(ProductsListInteractor::class.java)
        RxJavaPlugins.setErrorHandler {}
    }

    @Test
    fun testProgress() {

        Mockito.`when`(interactor.getItems())
            .thenReturn(Single.never())

        viewModel = TypesViewModel(
            router, interactor
        )
        rule.testScheduler.triggerActions()
        assertEquals(false, viewModel.error.get())
        assertEquals(true, viewModel.loading.get())
    }

    @Test
    fun testGetData() {
        Mockito.`when`(interactor.getItems())
            .thenReturn(Single.just(listOf(ListItem(true, "0", "", "0", ""))))

        viewModel = TypesViewModel(
            router, interactor
        )

        assertEquals(false, viewModel.loading.get())
        assertEquals(1, viewModel.items.get()?.size)
    }

    @Test
    fun testErrorGettingData() {
        Mockito.`when`(interactor.getItems())
            .thenReturn(Single.error(RuntimeException()))

        viewModel = TypesViewModel(
            router, interactor
        )

        assertEquals(true, viewModel.error.get())
        assertEquals(false, viewModel.loading.get())
    }

    @Test
    fun testOnClickedCategory() {
        Mockito.`when`(interactor.getItems())
            .thenReturn(Single.never())

        viewModel = TypesViewModel(
            router, interactor
        )
        viewModel.onItemClicked(ListItem(true, "0", "0", "", ""))
        Mockito.verifyNoMoreInteractions(router)
    }

    @Test
    fun testOnClickedProduct() {
        Mockito.`when`(interactor.getItems())
            .thenReturn(Single.never())

        viewModel = TypesViewModel(
            router, interactor
        )
        viewModel.onItemClicked(ListItem(false, "0", "0", "", ""))
        Mockito.verify(router, Mockito.times(1)).openDetail("0", "0")
        Mockito.verifyNoMoreInteractions(router)
    }

}