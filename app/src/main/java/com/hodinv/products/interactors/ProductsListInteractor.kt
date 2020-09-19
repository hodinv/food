package com.hodinv.products.interactors

import com.hodinv.products.model.ListItem
import com.hodinv.products.model.Product
import io.reactivex.Single

interface ProductsListInteractor {
    fun getItems(): Single<List<ListItem>>
    fun getProduct(id: String, categoryId: String): Single<Product>
}