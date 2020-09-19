package com.hodinv.products.services.repository

import com.hodinv.products.TestSchedulerRule
import com.hodinv.products.model.Product
import com.hodinv.products.model.ProductType
import com.hodinv.products.model.SalePrice
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductsRepositoryImplTest {

    private lateinit var repository: ProductsRepository

    @Rule
    @JvmField
    val rule = TestSchedulerRule()

    val list = listOf(
        ProductType(
            "1", "1", "", listOf(
                Product("1", "1", "1_1", "", "", SalePrice("", ""))
            )
        ),
        ProductType(
            "2", "2", "", listOf(
                Product("1", "2", "2_1", "", "", SalePrice("", ""))
            )
        )
    )

    @Before
    fun prepare() {
        repository = ProductsRepositoryImpl()
    }

    @Test
    fun testSetReady() {
        val firstCheck = repository.isReady().test()
        firstCheck.assertComplete()
        assertEquals(false, firstCheck.values()[0])

        val performe = repository.setList(list).test()
        performe.assertComplete()

        val secondCheck = repository.isReady().test()
        secondCheck.assertComplete()
        assertEquals(true, secondCheck.values()[0])
    }

    @Test
    fun tesSetReadyWithEmptyList() {
        val firstCheck = repository.isReady().test()
        firstCheck.assertComplete()
        assertEquals(false, firstCheck.values()[0])

        val performe = repository.setList(emptyList()).test()
        performe.assertComplete()

        val secondCheck = repository.isReady().test()
        secondCheck.assertComplete()
        assertEquals(true, secondCheck.values()[0])
    }

    @Test
    fun testGetList() {
        val setList = repository.setList(list).test()
        setList.assertComplete()

        val getList = repository.getList().test()
        getList.assertComplete()

        assertEquals(list, getList.values()[0])
    }

    @Test
    fun testGetItemById() {
        val setList = repository.setList(list).test()
        setList.assertComplete()

        val getFirst = repository.getProductById("1", "1").test()
        getFirst.assertComplete()
        assertEquals("1_1", getFirst.values()[0].name)

        val getSecond = repository.getProductById("1", "2").test()
        getSecond.assertComplete()
        assertEquals("2_1", getSecond.values()[0].name)

    }

}