package com.hodinv.products.mvvm

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseMvvmViewModel<Router : MvvmRouter>(val router: Router) : ViewModel() {
    private val disposables = CompositeDisposable()

    fun Disposable.toComposite(): Disposable {
        disposables.add(this)
        return this
    }

    public override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}