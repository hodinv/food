package com.hodinv.products.model

data class ProductType(
    val id: String,
    val name: String,
    val description: String,
    val products: List<Product>
)