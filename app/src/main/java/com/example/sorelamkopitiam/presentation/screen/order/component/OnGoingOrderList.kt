package com.example.sorelamkopitiam.presentation.screen.order.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sorelamkopitiam.domain.model.OrderItem

@Composable
fun OnGoingOrderList(
    orders: List<OrderItem>,
    onComplete: (OrderItem) -> Unit
) {
    val reversedOrders = orders.reversed()

    if (orders.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No OnGoing Orders", color = Color.Gray)
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            items(reversedOrders) { order ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7FA)),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(order.date, fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("${order.items} (Qty: ${order.quantity})")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Rp ${order.totalPrice}")
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = { onComplete(order) },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B))
                        ) {
                            Text("Mark as Complete", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}
