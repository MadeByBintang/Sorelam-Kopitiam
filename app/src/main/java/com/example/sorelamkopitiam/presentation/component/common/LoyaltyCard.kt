package com.example.sorelamkopitiam.presentation.component.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sorelamkopitiam.R
import com.example.sorelamkopitiam.presentation.viewmodel.LoyaltyCardViewModel

@Composable
fun LoyaltyCard(
    modifier: Modifier = Modifier,
    viewModel: LoyaltyCardViewModel = hiltViewModel()
) {
    val loyalty by viewModel.loyaltyCount.collectAsState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp, vertical = 12.dp)
            .background(color = Color(0xFF007042), shape = RoundedCornerShape(16.dp))
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.loyalty_card),
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "${loyalty % 8} / 8",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .padding(vertical = 24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(1.dp, Alignment.CenterHorizontally)
            ) {
                repeat(8) { index ->
                    val filled = index < (loyalty % 8)
                    val iconRes = if (filled) {
                        R.drawable.coffee_cup_fill
                    } else {
                        R.drawable.coffee_cup_unfill
                    }

                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = stringResource(R.string.loyalty_cup, index + 1),
                        modifier = Modifier.size(34.dp),
                        colorFilter = if (!filled) ColorFilter.tint(Color.Gray) else null
                    )
                }
            }
        }
    }
}