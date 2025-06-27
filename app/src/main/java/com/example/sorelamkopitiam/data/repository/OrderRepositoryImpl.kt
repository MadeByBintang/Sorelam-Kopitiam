package com.example.sorelamkopitiam.data.repository

import com.example.sorelamkopitiam.data.local.dao.OrderDao
import com.example.sorelamkopitiam.data.mapper.toOrderEntity
import com.example.sorelamkopitiam.data.mapper.toOrderItem
import com.example.sorelamkopitiam.domain.model.OrderItem
import com.example.sorelamkopitiam.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val dao: OrderDao
) : OrderRepository {
    override fun getOrdersByStatus(status: String): Flow<List<OrderItem>> {
        return dao.getOrdersByStatus(status).map { list ->
            list.map { it.toOrderItem() }
        }
    }

    override suspend fun insertOrder(order: OrderItem) {
        dao.insertOrder(order.toOrderEntity())
    }

    override suspend fun updateOrderStatus(orderId: Int, newStatus: String) {
        dao.updateOrderStatus(orderId, newStatus)
    }

    override suspend fun deleteOrderById(orderId: Int) {
        dao.deleteOrderById(orderId)
    }
}
