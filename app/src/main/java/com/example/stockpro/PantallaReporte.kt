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
fun PantallaReporte(
    viewModel: StockViewModel,
    onVolver: () -> Unit
) {
    val totalInventario = viewModel.calcularValorTotalInventario()
    val productosEnCero = viewModel.obtenerProductosEnCero()

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text("Reporte Financiero", fontSize = 22.sp,
            fontWeight = FontWeight.Bold, color = LilaPrincipal)

        Spacer(modifier = Modifier.height(48.dp))

        // Card principal
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = LilaClaro),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Capital Invertido Total", color = LilaOscuro,
                    fontSize = 14.sp, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$${"%.2f".format(totalInventario)}",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = LilaPrincipal
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Card secundaria
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFEF2F2)),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Productos con stock en cero", color = Color(0xFFDC2626),
                    fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text("$productosEnCero", fontSize = 40.sp,
                    fontWeight = FontWeight.Bold, color = Color(0xFFDC2626))
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedButton(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = LilaPrincipal)
        ) {
            Text("Volver al Catálogo", fontWeight = FontWeight.Bold)
        }
    }
}