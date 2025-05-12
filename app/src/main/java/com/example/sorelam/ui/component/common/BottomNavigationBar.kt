package com.example.sorelam.ui.component.common

import com.example.sorelam.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import com.example.sorelam.R.string as Strings


@Composable
fun BottomNavigationBar(
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    onItemSelected: (Int) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp, start = 24.dp, end = 24.dp)
            .height(70.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF))
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
                stringResource(id = Strings.nav_store),
                stringResource(id = Strings.nav_gift),
                stringResource(id = Strings.nav_receipt)
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