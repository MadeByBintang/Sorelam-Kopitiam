package com.example.sorelamkopitiam.data.repository

import com.example.sorelamkopitiam.domain.model.CoffeeItem
import com.example.sorelamkopitiam.domain.repository.CoffeeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CoffeeRepositoryImpl : CoffeeRepository {
    override fun getCoffeeMenu(): Flow<List<CoffeeItem>> = flow {
        emit(
            listOf(
                CoffeeItem(1, "Cappuccino", com.example.sorelamkopitiam.R.drawable.cappuccino),
                CoffeeItem(2, "Mocha", com.example.sorelamkopitiam.R.drawable.mocha),
                CoffeeItem(3, "Flat White", com.example.sorelamkopitiam.R.drawable.flat_white)
            )
        )
    }
}