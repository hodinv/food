package com.hodinv.products

import androidx.lifecycle.LiveData
import com.hodinv.products.mvvm.SingleLiveEvent
import com.hodinv.products.screens.Screens
import com.hodinv.products.screens.product.ProductRouter
import com.hodinv.products.screens.types.TypesRouter

class MainViewModel : TypesRouter, ProductRouter {
    private val postNextScreen: SingleLiveEvent<Screens> = SingleLiveEvent()
    val nextScreen: LiveData<Screens>
        get() = postNextScreen

    override fun openDetail(id: String, categoryId: String) {
        postNextScreen.value = Screens.Product(id, categoryId)
    }

}