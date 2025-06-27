package com.example.sorelamkopitiam.presentation.component.topbar

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sorelamkopitiam.R

@Composable
fun TopBar(
    userName: String,
    onCartClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = stringResource(R.string.good_morning),
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = userName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onCartClick) {
                Icon(
                    painter = painterResource(id = R.drawable.cart_outline),
                    contentDescription = stringResource(R.string.cart)
                )
            }
            IconButton(onClick = onProfileClick) {
                Icon(
                    painter = painterResource(id = R.drawable.account_outline),
                    contentDescription = stringResource(R.string.profile),
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}
