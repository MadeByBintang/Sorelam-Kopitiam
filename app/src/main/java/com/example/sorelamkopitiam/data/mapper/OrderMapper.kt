package com.example.sorelamkopitiam.data.mapper

import com.example.sorelamkopitiam.data.local.entity.OrderEntity
import com.example.sorelamkopitiam.domain.model.OrderItem

fun OrderEntity.toOrderItem() = OrderItem(
    id = id,
    items = items,
    quantity = quantity,
    totalPrice = totalPrice,
    date = date,
    status = status,
    shot = shot,
    size = size,
    ice = ice
)

fun OrderItem.toOrderEntity() = OrderEntity(
    id = id,
    items = items,
    quantity = quantity,
    totalPrice = totalPrice,
    date = date,
    status = status,
    shot = shot,
    size = size,
    ice = ice
)
