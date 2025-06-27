package com.example.sorelamkopitiam.domain.repository

import com.example.sorelamkopitiam.domain.model.RedeemItem
import kotlinx.coroutines.flow.Flow

interface RedeemRepository {
    fun getAllRedeemItems(): Flow<List<RedeemItem>>
}
