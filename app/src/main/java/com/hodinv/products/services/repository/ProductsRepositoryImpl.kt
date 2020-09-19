package com.hodinv.products.services.repository

import com.hodinv.products.model.Product
import com.hodinv.products.model.ProductType
import io.reactivex.Completable
import io.reactivex.Single

class ProductsRepositoryImpl : ProductsRepository {

    private var ready: Boolean = false

    private var data: List<ProductType> = emptyList()
        set(value) {
            ready = true
            field = value
        }

    override fun isReady(): Single<Boolean> {
        return Single.just(ready)
    }

    override fun setList(list: List<ProductType>): Completable {
        return Completable.fromAction {
            data = list
        }
    }

    override fun getList(): Single<List<ProductType>> {
        return Single.just(Unit).map { data }
    }

    override fun getProductById(id: String, categoryId: String): Single<Product> {
        return Single.just(Unit).map {
            data.first {
                it.id == categoryId
            }.products.first {
                it.id == id
            }
        }
    }
}