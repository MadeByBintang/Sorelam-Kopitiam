package com.example.sorelam.ui.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sorelam.R
import androidx.compose.ui.res.stringResource
import com.example.sorelam.R.string as Strings


@Composable
fun OrderConfirmationDialogContent(
    onConfirm: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Text(stringResource(id = Strings.order_confirmation), fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(30.dp))
        Text(stringResource(id = Strings.payment_method), fontWeight = FontWeight.Medium)
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
                onClick = { /* no-op */ },
                modifier = Modifier.scale(1.2f),
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(stringResource(id = Strings.cash), fontWeight = FontWeight.Medium)
                Text(
                    text = stringResource(id = Strings.cash_desc),
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.money),
                contentDescription = stringResource(id = Strings.cash_icon),
                modifier = Modifier.size(35.dp),
                tint = Color(0xFF007042)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(stringResource(id = Strings.subtotal), fontWeight = FontWeight.Medium)
            Text(stringResource(id = Strings.subtotal_value), fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(stringResource(id = Strings.tax), fontWeight = FontWeight.Medium)
            Text(stringResource(id = Strings.tax_value), fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(stringResource(id = Strings.delivery_fee), fontWeight = FontWeight.Medium)
            Text(stringResource(id = Strings.delivery_fee_value), fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    stringResource(id = Strings.total_price),
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray,
                    fontSize = 16.sp
                )
                Text(
                    stringResource(id = Strings.total_price_value_order),
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
                Text(stringResource(id = Strings.confirm_order), color = Color.White)
            }
        }
    }
}