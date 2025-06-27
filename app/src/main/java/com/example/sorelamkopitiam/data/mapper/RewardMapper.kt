package com.example.sorelamkopitiam.data.mapper

import com.example.sorelamkopitiam.data.local.entity.RewardEntity
import com.example.sorelamkopitiam.domain.model.RewardItem

fun RewardEntity.toReward(): RewardItem {
    return RewardItem(
        id = id,
        title = title,
        caption = caption,
        date = date,
        points = points
    )
}
