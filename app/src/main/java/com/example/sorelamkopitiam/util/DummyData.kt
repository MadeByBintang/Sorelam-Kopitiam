package com.example.sorelamkopitiam.util

import com.example.sorelamkopitiam.R
import com.example.sorelamkopitiam.domain.model.CoffeeItem

val dummyCoffeeMenu = listOf(
    CoffeeItem(1, "Cappuccino", R.drawable.cappuccino),
    CoffeeItem(2, "Mocha", R.drawable.mocha),
    CoffeeItem(3, "Flat White", R.drawable.flat_white),
    CoffeeItem(4, "Espresso", R.drawable.cappuccino),
    CoffeeItem(5, "Latte", R.drawable.cappuccino),
    CoffeeItem(6, "Americano", R.drawable.mocha),
    CoffeeItem(7, "Macchiato", R.drawable.flat_white),
    CoffeeItem(8, "Affogato", R.drawable.cappuccino),
)

fun getCoffeeItemById(id: Int): CoffeeItem? {
    return dummyCoffeeMenu.find { it.id == id }
}
