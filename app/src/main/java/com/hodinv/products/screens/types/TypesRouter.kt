package com.hodinv.products.screens.types

import com.hodinv.products.mvvm.MvvmRouter

interface TypesRouter : MvvmRouter {
    fun openDetail(id: String, categoryId: String)
}