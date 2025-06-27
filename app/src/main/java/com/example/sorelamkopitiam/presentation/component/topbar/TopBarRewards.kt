package com.example.sorelamkopitiam.presentation.component.topbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sorelamkopitiam.R

@Composable
fun TopBarRewards() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 49.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.rewards),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
