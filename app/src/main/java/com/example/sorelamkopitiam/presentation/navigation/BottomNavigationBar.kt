package com.example.sorelamkopitiam.presentation.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sorelamkopitiam.R

@Composable
fun BottomNavigationBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp, start = 24.dp, end = 24.dp)
            .height(70.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val icons = listOf(
                R.drawable.storefront_outline,
                R.drawable.gift_outline,
                R.drawable.receipt
            )
            val contentDescriptions = listOf(
                stringResource(id = R.string.nav_store),
                stringResource(id = R.string.nav_gift),
                stringResource(id = R.string.nav_receipt)
            )

            icons.forEachIndexed { index, iconRes ->
                IconButton(onClick = { onItemSelected(index) }) {
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = contentDescriptions[index],
                        tint = if (selectedIndex == index) Color(0xFF2C6A46) else Color.Black,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    }
}
