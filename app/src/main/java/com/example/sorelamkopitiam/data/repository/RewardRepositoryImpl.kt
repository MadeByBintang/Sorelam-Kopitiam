package com.example.sorelamkopitiam.data.repository

import com.example.sorelamkopitiam.data.local.dao.RewardDao
import com.example.sorelamkopitiam.data.local.entity.RewardEntity
import com.example.sorelamkopitiam.data.mapper.toReward
import com.example.sorelamkopitiam.domain.model.RewardItem
import com.example.sorelamkopitiam.domain.repository.RewardsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.collections.map

class RewardsRepositoryImpl @Inject constructor(
    private val dao: RewardDao
) : RewardsRepository {

    override fun getAllRewards(): Flow<List<RewardItem>> {
        return dao.getAllRewards().map { list -> list.map { it.toReward() } }
    }

    override suspend fun insertReward(reward: RewardItem) {
        dao.insertReward(
            RewardEntity(
                id = reward.id,
                title = reward.title,
                caption = reward.caption,
                date = reward.date,
                points = reward.points,
                isRedeem = reward.isRedeem
            )
        )
    }

    override suspend fun deleteReward(id: Int) {
        dao.deleteReward(RewardEntity(id = id, title = "", caption = "", date = "", points = 0))
    }

    override suspend fun clearRewards() {
        dao.clearRewards()
    }

    override suspend fun clearLoyalty() {
        dao.deleteAllNonRedeem()
    }
}
