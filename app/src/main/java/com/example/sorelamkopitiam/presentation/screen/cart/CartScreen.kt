package com.example.sorelamkopitiam.presentation.screen.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sorelamkopitiam.R
import com.example.sorelamkopitiam.presentation.component.cart.CartItemRow
import com.example.sorelamkopitiam.presentation.component.topbar.TopBarCart
import com.example.sorelamkopitiam.presentation.navigation.Screen
import com.example.sorelamkopitiam.presentation.screen.cart.component.OrderConfirmationDialogContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navController: NavHostController,
    cartViewModel: CartViewModel = hiltViewModel() // ✅ Tambahkan ini
) {
    val cartItems by cartViewModel.cartItems.collectAsState()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    // 🔥 Bottom Sheet Konfirmasi Pesanan
    if (cartViewModel.showSheet) {
        ModalBottomSheet(
            onDismissRequest = { cartViewModel.toggleSheet(false) },
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        ) {
            OrderConfirmationDialogContent(
                cartItems = cartItems,
                totalPrice = cartItems.sumOf { it.price * it.quantity },
                onConfirm = {
                    cartViewModel.checkout()
                    cartViewModel.toggleSheet(false)
                    navController.navigate(Screen.OrderSuccess.route) {
                        popUpTo(Screen.Home.route)
                    }
                }
            )
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 12.dp)
        ) {
            TopBarCart(onBackClick = { navController.popBackStack() })

            Spacer(modifier = Modifier.height(16.dp))

            if (cartItems.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No items in cart",
                        color = Color.Gray
                    )
                }
            }
            else {
                Column {
                    cartItems.forEach { item ->
                        CartItemRow(
                            item = item,
                            onAdd = {
                                cartViewModel.updateCart(item.copy(quantity = item.quantity + 1))
                            },
                            onRemove = {
                                if (item.quantity > 1) {
                                    cartViewModel.updateCart(item.copy(quantity = item.quantity - 1))
                                } else {
                                    cartViewModel.deleteCart(item)
                                }
                            },
                            onDelete = {
                                cartViewModel.deleteCart(item)
                            },
                            onEdit = {
                                navController.navigate(
                                    Screen.Detail.createRoute(
                                        productId = item.productId, // ID produk asli
                                        cartId = item.id // ID item cart yang mau diedit
                                    )
                                )
                            }

                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // 🔥 Total Price
            val totalPrice = cartItems.sumOf { it.price * it.quantity }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.total_price),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Rp $totalPrice",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF007042)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (cartItems.isNotEmpty()) {
                        cartViewModel.toggleSheet(true)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007042))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.cart_outline),
                    contentDescription = stringResource(id = R.string.checkout),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(id = R.string.checkout),
                    color = Color.White
                )
            }
        }
    }
}