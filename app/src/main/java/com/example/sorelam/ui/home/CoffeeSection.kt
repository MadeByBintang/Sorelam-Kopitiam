package com.example.sorelam.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sorelam.R

data class Coffee(val nameResId: Int, val imageRes: Int)

@Composable
fun CoffeeMenu(onCoffeeClick: () -> Unit = {}) {
    val coffeeTypes = listOf(
        Coffee(R.string.coffee_cappuccino, R.drawable.cappuccino),
        Coffee(R.string.coffee_mocha, R.drawable.mocha),
        Coffee(R.string.coffee_flat_white, R.drawable.flat_white)
    )

    val coffeeList = List(10) { index ->
        coffeeTypes[index % coffeeTypes.size]
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF007042), shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.choose_your_coffee),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                modifier = Modifier.padding(start = 12.dp, top = 20.dp, bottom = 18.dp)
            )

            LazyHorizontalGrid(
                rows = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(start = 6.dp, end = 6.dp)
            ) {
                items(coffeeList) { coffee ->
                    CoffeeItem(coffee, onCoffeeClick)
                }
            }
        }
    }
}

@Composable
fun CoffeeItem(coffee: Coffee, onCoffeeClick: () -> Unit) {
    val coffeeName = stringResource(id = coffee.nameResId)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(165.dp)
            .height(190.dp)
            .background(Color(0xFFFFFFFF), shape = RoundedCornerShape(16.dp))
            .padding(start = 12.dp)
    ) {
        Image(
            painter = painterResource(id = coffee.imageRes),
            contentDescription = coffeeName,
            modifier = Modifier
                .clickable { onCoffeeClick() }
                .size(100.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = coffeeName,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            maxLines = 2,
            modifier = Modifier.fillMaxWidth()
        )
    }
}