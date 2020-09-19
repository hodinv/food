package com.hodinv.products.screens.types

import androidx.databinding.ObservableField
import com.hodinv.products.interactors.ProductsListInteractor
import com.hodinv.products.model.ListItem
import com.hodinv.products.mvvm.BaseMvvmViewModel
import com.hodinv.products.screens.types.adapter.OnItemClicked
import io.reactivex.android.schedulers.AndroidSchedulers

class TypesViewModel(
    router: TypesRouter,
    productsListInteractor: ProductsListInteractor
) : BaseMvvmViewModel<TypesRouter>(router), OnItemClicked {

    val loading = ObservableField<Boolean>(true)
    val error = ObservableField<Boolean>(false)
    val items = ObservableField<List<ListItem>>(emptyList())

    init {
        productsListInteractor.getItems().observeOn(AndroidSchedulers.mainThread()).subscribe({
            loading.set(false)
            items.set(it)
        }, { ex ->
            ex.printStackTrace()
            error.set(true)
            loading.set(false)
        }).toComposite()
    }

    override fun onItemClicked(item: ListItem) {
        if (!item.category) {
            router.openDetail(item.id, item.parentId)
        }
    }


}