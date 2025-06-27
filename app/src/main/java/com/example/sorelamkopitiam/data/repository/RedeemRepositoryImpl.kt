package com.example.sorelamkopitiam.data.repository

import com.example.sorelamkopitiam.data.local.dao.RedeemDao
import com.example.sorelamkopitiam.data.mapper.toRedeemItem
import com.example.sorelamkopitiam.domain.model.RedeemItem
import com.example.sorelamkopitiam.domain.repository.RedeemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RedeemRepositoryImpl @Inject constructor(
    private val dao: RedeemDao
) : RedeemRepository {
    override fun getAllRedeemItems(): Flow<List<RedeemItem>> {
        return dao.getAllRedeemItems().map { list ->
            list.map { it.toRedeemItem() }
        }
    }
}
