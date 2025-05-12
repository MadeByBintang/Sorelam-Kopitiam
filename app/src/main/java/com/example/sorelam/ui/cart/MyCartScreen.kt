package com.example.sorelam.ui.cart

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sorelam.R
import com.example.sorelam.R.string as Strings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCartScreen(
    navController: NavHostController,
    innerPadding: PaddingValues = PaddingValues(0.dp)
) {
    val cartItems = listOf(
        CartItem(stringResource(id = Strings.coffee_americano), stringResource(id = Strings.price_3), R.drawable.mocha),
        CartItem(stringResource(id = Strings.coffee_cappuccino), stringResource(id = Strings.price_3), R.drawable.cappuccino),
        CartItem(stringResource(id = Strings.coffee_flat_white), stringResource(id = Strings.price_3), R.drawable.flat_white)
    )

    val showSheet = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    if (showSheet.value) {
        ModalBottomSheet(
            onDismissRequest = { showSheet.value = false },
            sheetState = sheetState,
            containerColor = Color.White,
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        ) {
            OrderConfirmationDialogContent(
                onConfirm = {
                    showSheet.value = false
                    navController.navigate("order_success")
                }
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 12.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = stringResource(id = Strings.back),
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        Text(
            text = stringResource(id = Strings.my_cart),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            cartItems.forEach { item ->
                CartItemRow(item = item)
                Spacer(modifier = Modifier.height(18.dp))
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(stringResource(id = Strings.total_price), fontWeight = FontWeight.Medium)
                Text(stringResource(id = Strings.total_price_value), fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(18.dp))

            Button(
                onClick = {
                    showSheet.value = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007042))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.cart_outline),
                    contentDescription = stringResource(id = Strings.checkout),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(stringResource(id = Strings.checkout), color = Color.White)
            }
        }
    }
}

@Composable
fun CartItemRow(item: CartItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF3F4F6))
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.name,
            modifier = Modifier
                .size(55.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                item.name,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Text(stringResource(id = Strings.item_detail), fontSize = 11.sp, color = Color.Gray)
            Text(stringResource(id = Strings.quantity), fontSize = 12.sp, color = Color.Gray)
        }
        Text(item.price, fontWeight = FontWeight.SemiBold)
    }
}

data class CartItem(val name: String, val price: String, val imageRes: Int)
