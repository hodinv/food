package com.hodinv.products.services.network

import com.hodinv.products.model.ProductType
import io.reactivex.Single
import retrofit2.http.GET

interface DataApi {
    @GET("/")
    fun getData(): Single<List<ProductType>>
}