package com.example.sorelamkopitiam.presentation.component.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sorelamkopitiam.R
import com.example.sorelamkopitiam.domain.model.CartItem

@Composable
fun CartItemRow(
    item: CartItem,
    onAdd: () -> Unit,
    onRemove: () -> Unit,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    isEditable: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF3F4F6))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 🔥 Gambar Produk
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.name,
            modifier = Modifier
                .size(55.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.width(8.dp))

        // 🔥 Nama & (Kalau Editable ada Quantity)
        Column(modifier = Modifier.weight(1f)) {
            Text(item.name, fontSize = 14.sp)

            Spacer(modifier = Modifier.height(4.dp))

            if (isEditable) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onRemove,
                        modifier = Modifier.size(26.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.remove),
                            contentDescription = "Kurangi",
                            modifier = Modifier.size(18.dp),
                            tint = Color.Black
                        )
                    }

                    Text(
                        "${item.quantity}",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )

                    IconButton(
                        onClick = onAdd,
                        modifier = Modifier.size(26.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add),
                            contentDescription = "Tambah",
                            modifier = Modifier.size(12.dp),
                            tint = Color.Black
                        )
                    }
                }
            } else {
                // 🔥 Kalau tidak editable, cuma tampilkan quantity
                Text(
                    "x${item.quantity}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

        // 🔥 Harga & Aksi
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Rp ${item.price * item.quantity}",
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                if (isEditable) {
                    IconButton(
                        onClick = onEdit,
                        modifier = Modifier.size(26.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.edit),
                            contentDescription = "Edit",
                            modifier = Modifier.size(18.dp),
                            tint = Color(0xFF007042)
                        )
                    }
                }
                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.size(26.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.delete),
                        contentDescription = "Hapus",
                        modifier = Modifier.size(20.dp),
                        tint = Color(0xFFFF4C4C)
                    )
                }
            }
        }
    }
}