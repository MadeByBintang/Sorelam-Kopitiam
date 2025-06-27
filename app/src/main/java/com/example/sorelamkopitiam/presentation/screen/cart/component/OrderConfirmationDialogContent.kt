package com.example.sorelamkopitiam.presentation.screen.cart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sorelamkopitiam.R
import com.example.sorelamkopitiam.domain.model.CartItem

@Composable
fun OrderConfirmationDialogContent(
    cartItems: List<CartItem>,
    totalPrice: Int,
    onConfirm: () -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {
        Text(
            stringResource(id = R.string.order_confirmation),
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(30.dp))
        Text(stringResource(id = R.string.payment_method), fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFF3F4F6))
                .padding(horizontal = 12.dp, vertical = 25.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = true,
                onClick = {},
                modifier = Modifier.scale(1.2f),
                colors = RadioButtonDefaults.colors(selectedColor = Color.Black)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(stringResource(id = R.string.cash), fontWeight = FontWeight.Medium)
                Text(
                    text = stringResource(id = R.string.cash_desc),
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.money),
                contentDescription = stringResource(id = R.string.cash_icon),
                modifier = Modifier.size(35.dp),
                tint = Color(0xFF007042)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        SummaryRow(R.string.subtotal, "Rp $totalPrice")
        SummaryRow(R.string.tax, "Rp 5.000")
        SummaryRow(R.string.delivery_fee, "Rp 10.000")

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    stringResource(id = R.string.total_price),
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray,
                    fontSize = 16.sp
                )
                Text(
                    text = "Rp ${totalPrice + 15000}", // Total + tax + delivery
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
            }

            Button(
                onClick = onConfirm,
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007042))
            ) {
                Text(stringResource(id = R.string.confirm_order), color = Color.White)
            }
        }
    }
}

@Composable
private fun SummaryRow(labelRes: Int, value: String) {
    Spacer(modifier = Modifier.height(12.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(stringResource(id = labelRes), fontWeight = FontWeight.Medium)
        Text(value, fontWeight = FontWeight.Bold)
    }
}
