package com.example.sorelamkopitiam.domain.repository

import com.example.sorelamkopitiam.domain.model.OrderItem
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun getOrdersByStatus(status: String): Flow<List<OrderItem>>
    suspend fun insertOrder(order: OrderItem)
    suspend fun updateOrderStatus(orderId: Int, newStatus: String)
    suspend fun deleteOrderById(orderId: Int) // ✅ Tambahkan
}
