package com.example.sorelamkopitiam.presentation.screen.redeem

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sorelamkopitiam.domain.model.RedeemItem
import com.example.sorelamkopitiam.presentation.component.topbar.TopBarRedeem
import com.example.sorelamkopitiam.presentation.screen.redeem.component.RedeemItemCard

@Composable
fun RedeemScreen(
    navController: NavHostController,
    viewModel: RedeemViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedItem by remember { mutableStateOf<RedeemItem?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var showSuccess by remember { mutableStateOf(false) } // ✅ Tambahan

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            TopBarRedeem(onBackClick = { navController.popBackStack() })
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(uiState.items) { item ->
                    RedeemItemCard(
                        item = item,
                        onClick = {
                            selectedItem = item
                            showDialog = true
                        }
                    )
                }
            }
        }
    }

    if (showDialog && selectedItem != null) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Redeem ${selectedItem!!.name}") },
            text = { Text("Are you sure you want to redeem this for ${selectedItem!!.points} pts?") },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.redeemItem(
                        item = selectedItem!!,
                        onSuccess = {
                            showDialog = false
                            showSuccess = true // ✅ Tampilkan dialog sukses
                        },
                        onFail = {
                            showDialog = false
                            showError = true
                        }
                    )
                }) {
                    Text("Redeem")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    if (showSuccess) {
        AlertDialog(
            onDismissRequest = { showSuccess = false },
            title = { Text("Success") },
            text = { Text("You have successfully redeemed ${selectedItem?.name}.") },
            confirmButton = {
                TextButton(onClick = { showSuccess = false }) {
                    Text("OK")
                }
            },
            dismissButton = {}
        )
    }

    if (showError) {
        AlertDialog(
            onDismissRequest = { showError = false },
            title = { Text("Not Enough Points") },
            text = { Text("You do not have enough points to redeem this item.") },
            confirmButton = {
                TextButton(onClick = { showError = false }) {
                    Text("OK")
                }
            },
            dismissButton = {}
        )
    }
}