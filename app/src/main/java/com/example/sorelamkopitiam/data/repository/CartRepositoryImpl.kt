package com.example.sorelamkopitiam.data.repository

import com.example.sorelamkopitiam.data.local.dao.CartDao
import com.example.sorelamkopitiam.data.mapper.toCartEntity
import com.example.sorelamkopitiam.data.mapper.toCartItem
import com.example.sorelamkopitiam.domain.model.CartItem
import com.example.sorelamkopitiam.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val dao: CartDao
) : CartRepository {
    override fun getAllCartItems(): Flow<List<CartItem>> {
        return dao.getAllCartItems().map { list -> list.map { it.toCartItem() } }
    }

    override fun getCartItemById(id: Int): Flow<CartItem?> {
        return dao.getCartItemById(id).map { it?.toCartItem() }
    }

    override suspend fun insertOrUpdate(item: CartItem) {
        dao.insertCartItem(item.toCartEntity())
    }

    override suspend fun delete(item: CartItem) {
        dao.deleteCartItem(item.toCartEntity())
    }

    override suspend fun clear() {
        dao.clearCart()
    }

    override suspend fun deleteByProductId(productId: Int) {
        dao.deleteByProductId(productId)
    }
    override suspend fun insertCartItem(item: CartItem) {
        dao.insertCartItem(item.toCartEntity())
    }

}