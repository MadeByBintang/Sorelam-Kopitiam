package com.example.sorelamkopitiam.data.mapper

import com.example.sorelamkopitiam.data.local.entity.OrderEntity
import com.example.sorelamkopitiam.domain.model.OrderItem

fun OrderEntity.toOrderItem(): OrderItem =
    OrderItem(
        id = id,
        date = date,
        items = items,
        quantity = quantity,
        totalPrice = totalPrice,
        status = status,
        shot = shot,
        size = size,
        ice = ice
    )

fun OrderItem.toOrderEntity(): OrderEntity =
    OrderEntity(
        id = id,
        date = date,
        items = items,
        quantity = quantity,
        totalPrice = totalPrice,
        status = status,
        shot = shot,
        size = size,
        ice = ice
    )
