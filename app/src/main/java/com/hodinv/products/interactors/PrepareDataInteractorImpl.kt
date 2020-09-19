package com.hodinv.products.interactors

import com.hodinv.products.services.network.DataApi
import com.hodinv.products.services.repository.ProductsRepository
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class PrepareDataInteractorImpl(
    private val api: DataApi,
    private val repository: ProductsRepository
) : PrepareDataInteractor {
    override fun prepare(): Completable {
        return repository.isReady().flatMapCompletable {
            if (it) {
                Completable.complete()
            } else {
                api.getData().flatMapCompletable { data ->
                    repository.setList(data)
                }.subscribeOn(Schedulers.io())
            }
        }
    }
}