package com.hodinv.products.interactors

import com.hodinv.products.TestSchedulerRule
import com.hodinv.products.model.Product
import com.hodinv.products.model.ProductType
import com.hodinv.products.model.SalePrice
import com.hodinv.products.services.repository.ProductsRepository
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class ProductsListInteractorImplTest {

    private lateinit var interactor: ProductsListInteractor
    private lateinit var prepareInteractor: PrepareDataInteractor
    private lateinit var repository: ProductsRepository

    @Rule
    @JvmField
    val rule = TestSchedulerRule()

    @Before
    fun prepare() {
        prepareInteractor = Mockito.mock(PrepareDataInteractor::class.java)
        repository = Mockito.mock(ProductsRepository::class.java)
        interactor = ProductsListInteractorImpl(
            prepareInteractor, repository
        )
    }


    @Test
    fun testGetItems() {
        val list = listOf(
            ProductType(
                "0",
                "Line1",
                "",
                listOf(Product("0", "0", "Line2", "", "", SalePrice("0", "0")))
            )
        )

        Mockito.`when`(prepareInteractor.prepare()).thenReturn(Completable.complete())
        Mockito.`when`(repository.getList()).thenReturn(Single.just(list))

        val test = interactor.getItems().test()
        rule.testScheduler.triggerActions()
        test.assertComplete()
        Assert.assertEquals(1, test.valueCount())
        val value = test.values()[0]
        Assert.assertEquals("Line1", value[0].name)
        Assert.assertEquals(true, value[0].category)
        Assert.assertEquals("Line2", value[1].name)
        Assert.assertEquals(false, value[1].category)

        Mockito.verify(repository, Mockito.times(1)).getList()
        Mockito.verify(prepareInteractor, Mockito.times(1)).prepare()
        Mockito.verifyNoMoreInteractions(repository)
        Mockito.verifyNoMoreInteractions(prepareInteractor)

    }

    @Test
    fun testGetProduct() {
        Mockito.`when`(repository.getProductById("0", "0")).thenReturn(Single.just(Product.NOTHING))

        val test = interactor.getProduct("0", "0").test()
        rule.testScheduler.triggerActions()
        test.assertComplete()
        test.assertResult(Product.NOTHING)

        Mockito.verify(repository, Mockito.times(1)).getProductById("0", "0")
        Mockito.verifyNoMoreInteractions(repository)
        Mockito.verifyNoMoreInteractions(prepareInteractor)
    }
}