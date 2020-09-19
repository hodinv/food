package com.hodinv.products.screens.product

import androidx.databinding.ObservableField
import com.hodinv.products.interactors.ProductsListInteractor
import com.hodinv.products.model.Product
import com.hodinv.products.mvvm.BaseMvvmViewModel
import io.reactivex.android.schedulers.AndroidSchedulers

class ProductViewModel(
    param: Param,
    productsListInteractor: ProductsListInteractor,
    router: ProductRouter
) : BaseMvvmViewModel<ProductRouter>(router) {
    val product = ObservableField<Product>(Product.NOTHING)

    init {
        productsListInteractor.getProduct(param.id, param.categoryId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { item ->
                product.set(item)
            }.toComposite()
    }

    data class Param(val id: String, val categoryId: String)
}