package com.example.sorelamkopitiam.presentation.screen.cart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sorelamkopitiam.domain.model.CartItem
import com.example.sorelamkopitiam.domain.model.OrderItem
import com.example.sorelamkopitiam.domain.repository.CartRepository
import com.example.sorelamkopitiam.domain.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val orderRepository: OrderRepository
) : ViewModel() {
    fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return sdf.format(Date())
    }
    val cartItems = cartRepository.getAllCartItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private var _showSheet by mutableStateOf(false)
    val showSheet get() = _showSheet

    fun toggleSheet(show: Boolean) {
        _showSheet = show
    }

    fun updateCart(item: CartItem) {
        viewModelScope.launch {
            cartRepository.insertOrUpdate(item)
        }
    }

    fun deleteCart(item: CartItem) {
        viewModelScope.launch {
            cartRepository.delete(item)
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            cartRepository.clear()
        }
    }

    fun checkout() {
        viewModelScope.launch {
            val date = getCurrentDate()
            cartItems.value.forEach { cartItem ->
                val order = OrderItem(
                    id = (System.currentTimeMillis() % Int.MAX_VALUE).toInt(),
                    date = date,
                    items = cartItem.name, // ✅ HANYA nama kopi TANPA caption
                    shot = cartItem.shot,
                    size = cartItem.size,
                    ice = cartItem.ice,
                    quantity = cartItem.quantity,
                    totalPrice = cartItem.price * cartItem.quantity,
                    status = "ongoing"
                )
                orderRepository.insertOrder(order)
            }
            clearCart()
        }
    }

    fun isFreeCoffee(item: CartItem): Boolean = item.productId == 9999
}