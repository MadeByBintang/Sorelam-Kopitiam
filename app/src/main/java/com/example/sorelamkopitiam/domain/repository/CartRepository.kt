package com.example.sorelamkopitiam.domain.repository

import com.example.sorelamkopitiam.domain.model.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getAllCartItems(): Flow<List<CartItem>>
    fun getCartItemById(id: Int): Flow<CartItem?>
    suspend fun insertOrUpdate(item: CartItem)
    suspend fun insertCartItem(item: CartItem)
    suspend fun delete(item: CartItem)
    suspend fun clear()
    suspend fun deleteByProductId(productId: Int)
}

