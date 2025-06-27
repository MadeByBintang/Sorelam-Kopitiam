package com.example.sorelamkopitiam.presentation.screen.rewards

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
import androidx.compose.ui.unit.sp
import com.example.sorelamkopitiam.domain.model.RewardItem

@Composable
fun HistoryRewards(histories: List<RewardItem>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Reward History",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 26.dp, vertical = 10.dp)
        )

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 26.dp)
                .fillMaxWidth()
                .padding(bottom = 103.dp)
        ) {
            items(histories) { reward ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f) // ⬅️ Ini yang membuat text kiri mengambil ruang yang fleksibel
                        ) {
                            Text(
                                text = reward.title,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp,
                            )
                            Text(
                                text = reward.caption,
                                color = Color.Gray,
                                fontSize = 10.sp
                            )
                            Text(
                                text = reward.date,
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp)) // ⬅️ Tambahkan ini agar tidak mepet dengan garis
                        }

                        Text(
                            text = "+${reward.points} pts",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2C6A46),
                            modifier = Modifier.padding(start = 12.dp) // ✅ Optional biar tidak terlalu nempel
                        )
                    }

                    HorizontalDivider(
                        color = Color.LightGray.copy(alpha = 0.8f),
                        thickness = 1.dp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}