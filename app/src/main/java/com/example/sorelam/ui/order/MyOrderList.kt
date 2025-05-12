package com.example.sorelam.ui.order

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sorelam.R

data class OrderHistory(val date: String, val name: String, val price: String)

@Composable
fun OnGoingOrder() {
    val orders = listOf(
        OrderHistory(
            date = stringResource(id = R.string.date_sample_2),
            name = stringResource(id = R.string.coffee_americano),
            price = stringResource(id = R.string.price_3)
        )
    ) + List(11) {
        OrderHistory(
            date = stringResource(id = R.string.date_sample_1),
            name = stringResource(id = R.string.coffee_cafe_latte),
            price = stringResource(id = R.string.price_3)
        )
    }

    OrderList(orders)
}

@Composable
fun HistoryOrder() {
    val cappuccinoOrders = List(13) {
        OrderHistory(
            date = stringResource(id = R.string.date_sample_1),
            name = stringResource(id = R.string.coffee_cappuccino),
            price = stringResource(id = R.string.price_3)
        )
    }

    val mochaOrder = OrderHistory(
        date = stringResource(id = R.string.date_sample_1),
        name = stringResource(id = R.string.coffee_mocha),
        price = stringResource(id = R.string.price_3)
    )

    OrderList(cappuccinoOrders + mochaOrder)
}

@Composable
fun OrderList(orders: List<OrderHistory>) {
    LazyColumn(
        modifier = Modifier
            .padding(start = 26.dp, end = 26.dp, top = 30.dp, bottom = 95.dp)
            .fillMaxSize()
    ) {
        itemsIndexed(orders) { _, order ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Text(
                    text = order.date,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = order.name, fontWeight = FontWeight.Medium)
                    Text(
                        text = order.price,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF007042)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.4f), thickness = 1.dp)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}