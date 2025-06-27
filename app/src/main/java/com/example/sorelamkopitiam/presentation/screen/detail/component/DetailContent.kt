package com.example.sorelamkopitiam.presentation.screen.detail.component

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sorelamkopitiam.R

@Composable
fun DetailContent(
    coffeeName: String,
    price: String,
    quantity: Int,
    shot: String,
    select: String,
    size: String,
    ice: String,
    onQuantityPlus: () -> Unit,
    onQuantityMinus: () -> Unit,
    onShotChange: (String) -> Unit,
    onSelectChange: (String) -> Unit,
    onSizeChange: (String) -> Unit,
    onIceChange: (String) -> Unit,
    onAddToCart: () -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 28.dp)) {

        // 🔥 Title & Quantity
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.widthIn(max = 240.dp)
            ) {
                Text(
                    text = coffeeName.substringBefore(" ("),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "$shot, $size, $select",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Row(
                modifier = Modifier
                    .border(1.dp, Color(0xFFD8D8D8).copy(alpha = 0.4f), RoundedCornerShape(24.dp))
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { if (quantity > 1) onQuantityMinus() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.remove),
                        contentDescription = "Remove",
                        modifier = Modifier.size(14.dp),
                        tint = Color.Black
                    )
                }

                Text(
                    quantity.toString(),
                    modifier = Modifier.width(20.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )

                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onQuantityPlus() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        modifier = Modifier.size(14.dp),
                        tint = Color.Black
                    )
                }
            }
        }

        DividerWithSpacing()

        // 🔥 Shot Option
        RowSection(title = stringResource(id = R.string.shot)) {
            OptionButton("Single", shot == "Single") { onShotChange("Single") }
            Spacer(Modifier.width(12.dp))
            OptionButton("Double", shot == "Double") { onShotChange("Double") }
        }

        DividerWithSpacing()

        // 🔥 Select Hot/Ice
        RowSection(title = stringResource(id = R.string.select)) {
            IconOption(R.drawable.coffee, select == "hot", 34.dp) { onSelectChange("hot") }
            Spacer(Modifier.width(30.dp))
            IconOption(R.drawable.ice_coffee, select == "ice", 45.dp) { onSelectChange("ice") }
        }

        DividerWithSpacing()

        // 🔥 Size Option
        RowSection(title = stringResource(id = R.string.size)) {
            IconOption(R.drawable.cold_coffee, size == "small", 30.dp) { onSizeChange("small") }
            Spacer(Modifier.width(12.dp))
            IconOption(R.drawable.cold_coffee, size == "medium", 40.dp) { onSizeChange("medium") }
            Spacer(Modifier.width(12.dp))
            IconOption(R.drawable.cold_coffee, size == "large", 50.dp) { onSizeChange("large") }
        }

        DividerWithSpacing()

        // 🔥 Ice Option
        if (select == "ice") {
            RowSection(title = stringResource(id = R.string.ice)) {
                IconOption(R.drawable.iced_1, ice == "less", 48.dp) { onIceChange("less") }
                Spacer(Modifier.width(12.dp))
                IconOption(R.drawable.iced_2, ice == "standart", 48.dp) { onIceChange("standart") }
                Spacer(Modifier.width(12.dp))
                IconOption(R.drawable.iced_3, ice == "extra", 48.dp) { onIceChange("extra") }
            }
        }


        Spacer(modifier = Modifier.weight(1f))

        // 🔥 Total Price
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(id = R.string.total_amount), fontWeight = FontWeight.Medium)
            Text(
                text = price,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF007042)
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        // 🔥 Add to Cart Button
        Button(
            onClick = onAddToCart,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0B7D4F))
        ) {
            Text(stringResource(id = R.string.add_to_cart), color = Color.White)
        }
    }
}


@Composable
private fun RowSection(
    title: String,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, fontWeight = FontWeight.Medium)
        Row(verticalAlignment = Alignment.CenterVertically, content = content)
    }
}

@Composable
private fun OptionButton(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .border(1.dp, Color(0xFFD8D8D8).copy(alpha = 0.4f), RoundedCornerShape(24.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = Color.Black,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}

@Composable
private fun IconOption(
    iconId: Int,
    selected: Boolean,
    size: Dp,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(size)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            colorFilter = ColorFilter.tint(
                if (selected) Color.Black else Color.Gray.copy(alpha = 0.4f)
            )
        )
    }
}

@Composable
private fun DividerWithSpacing() {
    Spacer(modifier = Modifier.height(18.dp))
    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.4f), thickness = 1.dp)
    Spacer(modifier = Modifier.height(18.dp))
}
