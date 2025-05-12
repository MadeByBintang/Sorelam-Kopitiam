package com.example.sorelam.ui.profile

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

data class RewardHistory(val name: Int, val date: Int, val points: Int)

val rewardHistories = listOf(
    RewardHistory(R.string.coffee_americano, R.string.date_sample_1, 12),
    RewardHistory(R.string.coffee_cafe_latte, R.string.date_sample_2, 12),
    RewardHistory(R.string.coffee_green_tea_latte, R.string.date_sample_3, 12),
    RewardHistory(R.string.coffee_flat_white, R.string.date_sample_4, 12),
    RewardHistory(R.string.coffee_americano, R.string.date_sample_1, 12),
    RewardHistory(R.string.coffee_cafe_latte, R.string.date_sample_2, 12),
    RewardHistory(R.string.coffee_green_tea_latte, R.string.date_sample_3, 12),
    RewardHistory(R.string.coffee_flat_white, R.string.date_sample_4, 12),
    RewardHistory(R.string.coffee_americano, R.string.date_sample_1, 12),
    RewardHistory(R.string.coffee_cafe_latte, R.string.date_sample_2, 12),
    RewardHistory(R.string.coffee_green_tea_latte, R.string.date_sample_3, 12),
    RewardHistory(R.string.coffee_flat_white, R.string.date_sample_4, 12),
)

@Composable
fun HistoryRewards(histories: List<RewardHistory>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.history_rewards_title),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 26.dp, vertical = 16.dp)
        )

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 26.dp)
                .fillMaxWidth()
                .padding(bottom = 95.dp)
        ) {
            itemsIndexed(histories) { _, it ->
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(text = stringResource(it.name), fontWeight = FontWeight.Medium)
                            Text(text = stringResource(it.date), fontSize = 12.sp, color = Color.Gray)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        Text(
                            text = stringResource(R.string.points_format, it.points),
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2C6A46)
                        )
                    }

                    HorizontalDivider(
                        color = Color.LightGray.copy(alpha = 0.4f),
                        thickness = 1.dp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}