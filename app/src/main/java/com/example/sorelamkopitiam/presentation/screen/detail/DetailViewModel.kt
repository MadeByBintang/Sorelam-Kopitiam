package com.example.sorelamkopitiam.presentation.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sorelamkopitiam.domain.model.CartItem
import com.example.sorelamkopitiam.domain.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState

    fun loadCartItem(cartId: Int) {
        viewModelScope.launch {
            cartRepository.getCartItemById(cartId).collect { item ->
                item?.let {
                    _uiState.value = DetailUiState(
                        quantity = it.quantity,
                        shot = it.shot,
                        select = it.select,
                        size = it.size,
                        ice = it.ice
                    )
                }
            }
        }
    }

    fun increaseQuantity() {
        _uiState.value = _uiState.value.copy(quantity = _uiState.value.quantity + 1)
    }

    fun decreaseQuantity() {
        if (_uiState.value.quantity > 1) {
            _uiState.value = _uiState.value.copy(quantity = _uiState.value.quantity - 1)
        }
    }

    fun setShot(shot: String) {
        _uiState.value = _uiState.value.copy(shot = shot)
    }

    fun setSelect(select: String) {
        _uiState.value = _uiState.value.copy(select = select)
    }

    fun setSize(size: String) {
        _uiState.value = _uiState.value.copy(size = size)
    }

    fun setIce(ice: String) {
        _uiState.value = _uiState.value.copy(ice = ice)
    }

    fun calculatePrice(): Int {
        val base = 30000
        val shotCost = if (_uiState.value.shot == "Double") 6000 else 0
        val selectCost = if (_uiState.value.select == "ice") 3000 else 0
        val sizeCost = when (_uiState.value.size) {
            "small" -> 0
            "medium" -> 5000
            "large" -> 10000
            else -> 0
        }
        val iceCost = if (_uiState.value.ice == "extra") 4000 else 0

        return (base + shotCost + selectCost + sizeCost + iceCost) * _uiState.value.quantity
    }

    fun saveToCart(
        cartId: Int? = null, // Untuk edit
        productId: Int,
        name: String,
        imageRes: Int,
        priceLabel: String
    ) {
        viewModelScope.launch {
            val cartItem = CartItem(
                id = cartId ?: (System.currentTimeMillis() % Int.MAX_VALUE).toInt(),
                productId = productId, // ✅ Penting!
                name = "$name (${_uiState.value.shot}, ${_uiState.value.size}, ${_uiState.value.select})",
                priceLabel = priceLabel,
                imageRes = imageRes,
                price = calculatePrice(),
                quantity = _uiState.value.quantity,
                shot = _uiState.value.shot,
                select = _uiState.value.select,
                size = _uiState.value.size,
                ice = _uiState.value.ice
            )
            cartRepository.insertOrUpdate(cartItem)
        }
    }
}