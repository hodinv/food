package com.hodinv.products.model

data class ListItem(
    val category: Boolean,
    val id: String,
    val parentId: String,
    val name: String,
    val image: String
)

fun ProductType.toListItem() = ListItem(true, id, "", name, "")
fun Product.toListItem() = ListItem(false, id, categoryId, name, url)