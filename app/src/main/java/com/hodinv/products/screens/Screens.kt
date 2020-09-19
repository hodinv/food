package com.hodinv.products.screens

sealed class Screens {
    class Types() : Screens()
    class Product(val id: String, val categoryId: String) : Screens()

}