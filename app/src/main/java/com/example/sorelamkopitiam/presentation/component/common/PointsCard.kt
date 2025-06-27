package com.example.sorelamkopitiam.presentation.component.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sorelamkopitiam.R

@Composable
fun PointsCard(
    points: Int,
    modifier: Modifier = Modifier,
    onRedeemClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp)
            .background(Color(0xFF007042), RoundedCornerShape(16.dp))
            .padding(24.dp)
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(
                text = stringResource(R.string.my_points),
                color = Color.White,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = points.toString(),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Box(
                    modifier = Modifier
                        .background(Color(0xFFA2CDE9).copy(alpha = 0.19f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .clickable { onRedeemClick() }
                ) {
                    Text(
                        text = stringResource(R.string.redeem_drinks),
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}