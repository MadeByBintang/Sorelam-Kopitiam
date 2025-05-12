package com.example.sorelam.ui.core

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sorelam.R
import com.example.sorelam.ui.component.topbar.TopBarDetail
import com.example.sorelam.R.string as Strings

@Composable
fun DetailScreen(navController: NavHostController) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            TopBarDetail(
                onBackClick = { navController.popBackStack() },
                onCartClick = { navController.navigate("cart") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(horizontal = 24.dp)
                    .background(
                        color = Color(0xFFF5F5F5),
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cappuccino),
                    contentDescription = stringResource(id = Strings.coffee_americano),
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(150.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            DetailContent()
        }
    }
}

@Composable
fun DetailContent() {
    var quantity by remember { mutableIntStateOf(1) }
    var shot by remember { mutableStateOf("Single") }
    var select by remember { mutableStateOf("ice") }
    var size by remember { mutableStateOf("medium") }
    var ice by remember { mutableStateOf("less") }

    Column(modifier = Modifier.padding(horizontal = 28.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(id = Strings.coffee_americano), fontSize = 16.sp, fontWeight = FontWeight.Medium)

            Row(
                modifier = Modifier
                    .border(1.dp, Color(0xFFD8D8D8).copy(alpha = 0.4f), RoundedCornerShape(24.dp))
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { if (quantity > 1) quantity-- },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.remove),
                        contentDescription = stringResource(id = Strings.remove),
                        modifier = Modifier.size(14.dp),
                        tint = Color.Black
                    )
                }

                Text(
                    quantity.toString(),
                    fontSize = 14.sp,
                    modifier = Modifier.width(20.dp),
                    textAlign = TextAlign.Center
                )

                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { quantity++ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = Strings.add),
                        modifier = Modifier.size(14.dp),
                        tint = Color.Black
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))
        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.4f), thickness = 1.dp)
        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(id = Strings.shot), fontWeight = FontWeight.Medium)

            Row(verticalAlignment = Alignment.CenterVertically) {
                ShotOption(stringResource(id = Strings.single), shot == "Single") { shot = "Single" }
                Spacer(modifier = Modifier.width(12.dp))
                ShotOption(stringResource(id = Strings.dabel), shot == "Double") { shot = "Double" }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.4f), thickness = 1.dp)
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(id = Strings.select), fontWeight = FontWeight.Medium)

            Row(verticalAlignment = Alignment.CenterVertically) {
                SelectIconOption(
                    iconId = R.drawable.coffee,
                    selected = select == "hot",
                    onClick = { select = "hot" },
                    size = 34.dp
                )
                Spacer(modifier = Modifier.width(30.dp))
                SelectIconOption(
                    iconId = R.drawable.ice_coffee,
                    selected = select == "ice",
                    onClick = { select = "ice" },
                    size = 45.dp
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.4f), thickness = 1.dp)
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(id = Strings.size), fontWeight = FontWeight.Medium)

            Row(verticalAlignment = Alignment.CenterVertically) {
                SizeIconOption(R.drawable.cold_coffee, size == "small", { size = "small" }, size = 30.dp)
                Spacer(modifier = Modifier.width(12.dp))
                SizeIconOption(R.drawable.cold_coffee, size == "medium", { size = "medium" }, size = 40.dp)
                Spacer(modifier = Modifier.width(12.dp))
                SizeIconOption(R.drawable.cold_coffee, size == "large", { size = "large" }, size = 50.dp)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.4f), thickness = 1.dp)
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(id = Strings.ice), fontWeight = FontWeight.Medium)

            Row(verticalAlignment = Alignment.CenterVertically) {
                IceOption(R.drawable.iced_1, ice == "less", { ice = "less" })
                Spacer(modifier = Modifier.width(12.dp))
                IceOption(R.drawable.iced_2, ice == "standart", { ice = "standart" })
                Spacer(modifier = Modifier.width(12.dp))
                IceOption(R.drawable.iced_3, ice == "extra", { ice = "extra" })
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(id = Strings.total_amount), fontWeight = FontWeight.Medium)
            Text(
                text = stringResource(id = Strings.price_3),
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Button(
            onClick = { /* add to cart */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0B7D4F))
        ) {
            Text(stringResource(id = Strings.add_to_cart), color = Color.White)
        }
    }
}

@Composable
fun ShotOption(label: String, selected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .border(1.dp, Color(0xFFD8D8D8).copy(alpha = 0.4f), RoundedCornerShape(24.dp))
            .clickable { onClick() }
            .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 7.dp),
            text = label,
            color = Color.Black,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}

@Composable
fun SelectIconOption(iconId: Int, selected: Boolean, onClick: () -> Unit, size: Dp = 34.dp) {
    Box(
        modifier = Modifier.size(size).clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            colorFilter = ColorFilter.tint(if (selected) Color.Black else Color.Gray.copy(alpha = 0.4f))
        )
    }
}

@Composable
fun SizeIconOption(iconId: Int, selected: Boolean, onClick: () -> Unit, size: Dp) {
    Box(
        modifier = Modifier.size(size).clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            colorFilter = ColorFilter.tint(if (selected) Color.Black else Color.Gray.copy(alpha = 0.4f))
        )
    }
}

@Composable
fun IceOption(iconId: Int, selected: Boolean, onClick: () -> Unit, size: Dp = 48.dp) {
    Box(
        modifier = Modifier.size(size).clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            colorFilter = ColorFilter.tint(if (selected) Color.Black else Color.Gray.copy(alpha = 0.4f))
        )
    }
}