package com.example.sorelamkopitiam.data.mapper

import com.example.sorelamkopitiam.data.local.entity.CartEntity
import com.example.sorelamkopitiam.domain.model.CartItem

fun CartEntity.toCartItem() = CartItem(
    id = id,
    productId = productId,
    name = name,
    priceLabel = priceLabel,
    price = price,
    quantity = quantity,
    imageRes = imageRes,
    shot = shot,
    select = select,
    size = size,
    ice = ice
)

fun CartItem.toCartEntity() = CartEntity(
    id = id,
    productId = productId,
    name = name,
    priceLabel = priceLabel,
    price = price,
    quantity = quantity,
    imageRes = imageRes,
    shot = shot,
    select = select,
    size = size,
    ice = ice
)
