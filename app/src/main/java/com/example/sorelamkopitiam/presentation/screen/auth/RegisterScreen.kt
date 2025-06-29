package com.example.sorelamkopitiam.presentation.screen.auth

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sorelamkopitiam.R
import com.example.sorelamkopitiam.presentation.navigation.Screen

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    val authState by viewModel.authState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = authState) {
        if (authState.isSuccess) {
            Toast.makeText(context, "Sign Up Success!", Toast.LENGTH_SHORT).show()
            navController.navigate(Screen.Main.route) {
                popUpTo(Screen.Auth.route) { inclusive = true }
            }
        }
        authState.error?.let {
            Toast.makeText(context, "Error: $it", Toast.LENGTH_LONG).show()
        }
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "Back",
                    // --- TAMBAHKAN MODIFIER UKURAN DI SINI ---
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            Text("Sign up", fontSize = 32.sp, fontWeight = FontWeight.Bold)
            Text("Create an account here", color = Color.Gray)

            Spacer(modifier = Modifier.height(32.dp))

            // Username
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                leadingIcon = { Icon(painterResource(id = R.drawable.user), contentDescription = null, modifier = Modifier.size(24.dp)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email address") },
                leadingIcon = { Icon(painterResource(id = R.drawable.email), contentDescription = null, modifier = Modifier.size(24.dp)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Password
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                leadingIcon = { Icon(painterResource(id = R.drawable.edit_text), contentDescription = null, modifier = Modifier.size(24.dp)) },
                trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            painter = if (isPasswordVisible) painterResource(id = R.drawable.visible) else painterResource(id = R.drawable.eye),
                            contentDescription = "Toggle password visibility",
                            // --- 1. PERBAIKI UKURAN IKON MATA DI SINI ---
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text("By signing up you agree with our Terms of Use", fontSize = 12.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(32.dp))

            if (authState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.End))
            } else {
                Button(
                    onClick = { viewModel.onSignUpClicked(username, email, password) },
                    modifier = Modifier.size(64.dp).align(Alignment.End),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007042))
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = "Sign Up",
                        tint = Color.White,
                        // --- 2. PERBAIKI UKURAN IKON PANAH DI SINI ---
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Already a member?")
                TextButton(onClick = { navController.popBackStack() }) {
                    Text("Sign in", color = Color(0xFF007042))
                }
            }
        }
    }
}