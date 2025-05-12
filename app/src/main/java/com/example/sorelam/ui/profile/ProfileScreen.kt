package com.example.sorelam.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.navigation.NavController
import com.example.sorelam.R
import com.example.sorelam.ui.component.topbar.TopBarProfile

@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 12.dp)
        ) {
            TopBarProfile(onBackClick = { navController.popBackStack() })

            Spacer(modifier = Modifier.height(16.dp))

            ProfileItem(
                iconRes = R.drawable.user,
                label = stringResource(R.string.label_full_name),
                value = stringResource(R.string.value_full_name)
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProfileItem(
                iconRes = R.drawable.telephone,
                label = stringResource(R.string.label_phone_number),
                value = stringResource(R.string.value_phone_number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProfileItem(
                iconRes = R.drawable.email,
                label = stringResource(R.string.label_email),
                value = stringResource(R.string.value_email)
            )
        }
    }
}

@Composable
fun ProfileItem(
    iconRes: Int,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Color(0xFFF3F4F6), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                modifier = Modifier.size(20.dp),
                colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color(0xFF007042))
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = label, fontSize = 12.sp, color = Color.Gray)
            Text(
                text = value,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF007042)
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.edit_text),
            contentDescription = stringResource(R.string.edit),
            tint = Color(0xFF007042),
            modifier = Modifier
                .size(24.dp)
                .clickable { /* Edit action */ }
        )
    }
}