package com.hodinv.products.interactors

import com.hodinv.products.model.ListItem
import com.hodinv.products.model.Product
import com.hodinv.products.model.toListItem
import com.hodinv.products.services.repository.ProductsRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ProductsListInteractorImpl(
    val prepareDataInteractor: PrepareDataInteractor,
    val repository: ProductsRepository
) : ProductsListInteractor {
    override fun getItems(): Single<List<ListItem>> {
        return prepareDataInteractor.prepare().andThen(
            repository.getList().map {
                val result = ArrayList<ListItem>()
                it.forEach {
                    result.add(it.toListItem())
                    it.products.forEach {
                        result.add(it.toListItem())
                    }
                }
                result as List<ListItem>
            }.subscribeOn(Schedulers.io())
        )
    }

    override fun getProduct(id: String, categoryId: String): Single<Product> {
        return repository.getProductById(id, categoryId)
    }
}