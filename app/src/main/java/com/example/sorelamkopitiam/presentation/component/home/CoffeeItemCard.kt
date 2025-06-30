package com.example.sorelamkopitiam.presentation.component.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sorelamkopitiam.domain.model.CoffeeItem

@Composable
fun CoffeeItemCard(
    coffee: CoffeeItem,
    onCoffeeClick: (CoffeeItem) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(165.dp)
            .height(190.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .clickable { onCoffeeClick(coffee) }
            .padding(start = 12.dp)
    ) {
        Image(
            painter = painterResource(id = coffee.imageRes),
            contentDescription = coffee.name,
            modifier = Modifier
                .size(100.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = coffee.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            maxLines = 2,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
