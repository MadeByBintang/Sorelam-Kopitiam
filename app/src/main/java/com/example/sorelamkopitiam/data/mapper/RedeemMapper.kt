package com.example.sorelamkopitiam.data.mapper

import com.example.sorelamkopitiam.data.local.entity.RedeemEntity
import com.example.sorelamkopitiam.domain.model.RedeemItem

fun RedeemEntity.toRedeemItem() = RedeemItem(
    id = id,
    name = name,
    imageRes = imageRes,
    validUntil = validUntil,
    points = points
)
