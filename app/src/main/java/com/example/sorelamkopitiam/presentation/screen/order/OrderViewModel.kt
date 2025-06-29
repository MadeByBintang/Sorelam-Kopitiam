package com.example.sorelamkopitiam.presentation.screen.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sorelamkopitiam.domain.model.OrderItem
import com.example.sorelamkopitiam.domain.model.RewardItem
import com.example.sorelamkopitiam.domain.repository.OrderRepository
import com.example.sorelamkopitiam.domain.repository.RewardsRepository
import com.example.sorelamkopitiam.util.RewardUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val rewardsRepository: RewardsRepository
) : ViewModel() {

    private val _selectedTab = MutableStateFlow(0)
    val selectedTab: StateFlow<Int> = _selectedTab

    val ongoingOrders = orderRepository.getOrdersByStatus("ongoing").stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val historyOrders = orderRepository.getOrdersByStatus("history").stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun selectTab(index: Int) {
        _selectedTab.value = index
    }

    fun updateOrderToHistory(order: OrderItem) = viewModelScope.launch {
        orderRepository.updateOrderStatus(order.id, "history")

        // 🔥 FIX: Hanya tambahkan poin jika harga total lebih dari 0
        if (order.totalPrice > 0) {
            val title = order.items

            val caption = "${order.shot.ifEmpty { "-" }}, " +
                    "${order.size.ifEmpty { "-" }}, " +
                    "${order.ice.ifEmpty { "-" }} (x${order.quantity})"

            val points = RewardUtils.calculatePoints(order.totalPrice)

            rewardsRepository.insertReward(
                RewardItem(
                    id = 0,
                    title = title,
                    caption = caption,
                    date = RewardUtils.getCurrentDate(),
                    points = points,
                    isRedeem = false,
                    isStamp = true
                )
            )
        }
    }

    fun deleteHistoryOrder(orderId: Int) = viewModelScope.launch {
        orderRepository.deleteOrderById(orderId)
    }
}