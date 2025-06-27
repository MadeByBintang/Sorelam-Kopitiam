package com.example.sorelamkopitiam.domain.repository

import com.example.sorelamkopitiam.domain.model.CoffeeItem
import kotlinx.coroutines.flow.Flow

interface CoffeeRepository {
    fun getCoffeeMenu(): Flow<List<CoffeeItem>>
}
