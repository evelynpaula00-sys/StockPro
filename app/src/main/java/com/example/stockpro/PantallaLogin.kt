package com.example.stockpro

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun PantallaLogin(onIngresar: (String) -> Unit) {
    var nombre by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido a StockPro",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = LilaPrincipal
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Sistema de gestión de inventario",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del operario") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = LilaPrincipal,
                focusedLabelColor  = LilaPrincipal,
                cursorColor        = LilaPrincipal
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onIngresar(nombre) },
            enabled = nombre.length >= 3,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor         = LilaPrincipal,
                disabledContainerColor = LilaClaro
            )
        ) {
            Text("Ingresar al Sistema", fontWeight = FontWeight.Bold)
        }
    }
}

