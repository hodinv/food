package com.hodinv.products.services.repository

import com.hodinv.products.model.Product
import com.hodinv.products.model.ProductType
import io.reactivex.Completable
import io.reactivex.Single

interface ProductsRepository {
    fun isReady(): Single<Boolean>
    fun setList(list: List<ProductType>): Completable
    fun getList(): Single<List<ProductType>>
    fun getProductById(id: String, categoryId: String): Single<Product>
}