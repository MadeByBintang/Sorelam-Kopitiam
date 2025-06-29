package com.example.sorelamkopitiam.presentation.screen.rewards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sorelamkopitiam.R
import com.example.sorelamkopitiam.domain.model.CartItem
import com.example.sorelamkopitiam.domain.model.RewardItem
import com.example.sorelamkopitiam.domain.repository.CartRepository
import com.example.sorelamkopitiam.domain.repository.RewardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RewardsViewModel @Inject constructor(
    private val rewardsRepository: RewardsRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    val rewards: StateFlow<List<RewardItem>> = rewardsRepository.getAllRewards()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val loyaltyCount = rewards.map { rewardsList ->
        rewardsList.count { !it.isRedeem } % 8
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val points = rewards.map { rewardsList ->
        rewardsList.sumOf { if (it.isRedeem) -it.points else it.points }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    private val _showLoyaltyDialog = MutableStateFlow(false)
    val showLoyaltyDialog: StateFlow<Boolean> = _showLoyaltyDialog

    fun showLoyaltyDialog() {
        _showLoyaltyDialog.value = true
    }

    fun dismissLoyaltyDialog() {
        _showLoyaltyDialog.value = false
    }

    fun addFreeCoffeeToCart() {
        viewModelScope.launch {
            // Hapus Free Coffee jika sudah ada sebelumnya
            cartRepository.deleteByProductId(9999)

            // Tambahkan Free Coffee baru
            cartRepository.insertCartItem(
                CartItem(
                    id = 0,
                    productId = 9999,
                    name = "Free Coffee",
                    price = 0,
                    quantity = 1,
                    size = "-",
                    shot = "-",
                    ice = "-",
                    imageRes = R.drawable.cappuccino,
                    priceLabel = "FREE",
                    select = "-"
                )
            )
        }
    }


    // 🔥 Reset loyalty stamp (tanpa hapus reward poin)
    fun clearLoyaltyProgress() {
        viewModelScope.launch {
            val loyaltyStamps = rewards.value.filter { it.isStamp && !it.isRedeem }
            loyaltyStamps.take(8).forEach {
                rewardsRepository.deleteReward(it.id)
            }

            // Tambahkan history bahwa user redeem loyalty reward
            rewardsRepository.insertReward(
                RewardItem(
                    id = 0,
                    title = "Free Coffee",
                    caption = "Loyalty Reward Redeemed",
                    date = getCurrentDate(),
                    points = 0,
                    isRedeem = true,
                    isStamp = false // ✅ Ini bukan stamp
                )
            )
        }
    }


    // 🔥 Cek apakah loyalty mencapai 8, jika ya munculkan dialog
    fun checkLoyaltyReward() {
        viewModelScope.launch {
            val currentLoyalty = rewardsRepository.getAllRewards()
                .first()
                .count { !it.isRedeem }

            if (currentLoyalty != 0 && currentLoyalty % 8 == 0) {
                showLoyaltyDialog()
            }
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            rewardsRepository.clearRewards()
        }
    }

    fun deleteReward(id: Int) {
        viewModelScope.launch {
            rewardsRepository.deleteReward(id)
        }
    }


    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return sdf.format(Date())
    }
}