package com.example.stockpro

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantallaEdicion(
    productoId: Int,
    viewModel: StockViewModel,
    onVolver: () -> Unit
) {
    val producto = viewModel.obtenerProducto(productoId) ?: return
    var stockActual by remember { mutableStateOf(producto.stockActual) }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Text(producto.nombre, fontSize = 24.sp,
            fontWeight = FontWeight.Bold, color = LilaPrincipal)

        Spacer(modifier = Modifier.height(8.dp))

        Text(producto.descripcion, color = Color.Gray, fontSize = 14.sp)

        Spacer(modifier = Modifier.height(40.dp))

        Text("Stock actual", color = Color.Gray, fontSize = 14.sp)

        Text(
            text = "$stockActual",
            fontSize = 72.sp,
            fontWeight = FontWeight.Bold,
            color = if (stockActual < 5) Color.Red else LilaPrincipal
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            // Botón restar
            FilledIconButton(
                onClick = { stockActual-- },
                enabled = stockActual > 0,
                modifier = Modifier.size(64.dp),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = Color(0xFFDC2626),
                    disabledContainerColor = Color.LightGray
                )
            ) {
                Icon(Icons.Default.Remove, contentDescription = "Restar",
                    tint = Color.White, modifier = Modifier.size(32.dp))
            }

            // Botón sumar
            FilledIconButton(
                onClick = { stockActual++ },
                modifier = Modifier.size(64.dp),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = LilaPrincipal
                )
            ) {
                Icon(Icons.Default.Add, contentDescription = "Sumar",
                    tint = Color.White, modifier = Modifier.size(32.dp))
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = {
                viewModel.actualizarStock(productoId, stockActual)
                onVolver()
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = LilaPrincipal)
        ) {
            Text("Guardar y Volver", fontWeight = FontWeight.Bold)
        }
    }
}