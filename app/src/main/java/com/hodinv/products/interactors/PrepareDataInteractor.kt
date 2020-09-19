package com.hodinv.products.interactors

import io.reactivex.Completable

interface PrepareDataInteractor {
    fun prepare(): Completable
}