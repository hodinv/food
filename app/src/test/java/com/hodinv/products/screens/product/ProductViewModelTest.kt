package com.hodinv.products.screens.product

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hodinv.products.TestSchedulerRule
import com.hodinv.products.interactors.ProductsListInteractor
import com.hodinv.products.model.Product
import com.hodinv.products.model.SalePrice
import io.reactivex.Single
import io.reactivex.plugins.RxJavaPlugins
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito

class ProductViewModelTest {
    private lateinit var viewModel: ProductViewModel
    private lateinit var router: ProductRouter
    private lateinit var interactor: ProductsListInteractor

    @Rule
    @JvmField
    val rule = TestSchedulerRule()

    @Rule
    @JvmField
    val ruleInstantRun: TestRule = InstantTaskExecutorRule()

    @Before
    fun prepare() {
        router = Mockito.mock(ProductRouter::class.java)
        interactor = Mockito.mock(ProductsListInteractor::class.java)
        RxJavaPlugins.setErrorHandler {}
    }

    @Test
    fun testSetupData() {
        Mockito.`when`(interactor.getProduct("1", "2"))
            .thenReturn(Single.just(Product("1", "2", "2_1", "", "", SalePrice("", ""))))
        viewModel = ProductViewModel(ProductViewModel.Param("1", "2"), interactor, router)
        assertEquals("2_1", viewModel.product.get()?.name)
    }
}