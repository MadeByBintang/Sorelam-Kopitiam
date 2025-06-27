package com.example.sorelamkopitiam.presentation.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sorelamkopitiam.presentation.component.topbar.TopBarDetail
import com.example.sorelamkopitiam.presentation.navigation.Screen
import com.example.sorelamkopitiam.presentation.screen.detail.component.DetailContent
import com.example.sorelamkopitiam.util.getCoffeeItemById

@Composable
fun DetailScreen(
    navController: NavHostController,
    productId: Int,
    cartId: Int,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    val coffeeItem = remember { getCoffeeItemById(productId) }

    if (coffeeItem == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Item not found")
        }
        return
    }

    // 🔥 Load jika mode edit (cartId != -1)
    LaunchedEffect(cartId) {
        if (cartId != -1) {
            viewModel.loadCartItem(cartId)
        }
    }

    val totalPrice by remember(state) {
        derivedStateOf { viewModel.calculatePrice() }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            TopBarDetail(
                onBackClick = { navController.popBackStack() },
                onCartClick = { navController.navigate(Screen.Cart.route) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(horizontal = 24.dp)
                    .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = coffeeItem.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            DetailContent(
                coffeeName = coffeeItem.name,
                price = "Rp $totalPrice",
                quantity = state.quantity,
                shot = state.shot,
                select = state.select,
                size = state.size,
                ice = state.ice,
                onQuantityPlus = { viewModel.increaseQuantity() },
                onQuantityMinus = { viewModel.decreaseQuantity() },
                onShotChange = { viewModel.setShot(it) },
                onSelectChange = { viewModel.setSelect(it) },
                onSizeChange = { viewModel.setSize(it) },
                onIceChange = { viewModel.setIce(it) },
                onAddToCart = {
                    viewModel.saveToCart(
                        cartId = if (cartId != -1) cartId else null, // 🔥 Jika edit, pakai cartId lama
                        productId = coffeeItem.id,
                        name = coffeeItem.name,
                        imageRes = coffeeItem.imageRes,
                        priceLabel = "Rp $totalPrice"
                    )
                    navController.popBackStack()
                }
            )
        }
    }
}