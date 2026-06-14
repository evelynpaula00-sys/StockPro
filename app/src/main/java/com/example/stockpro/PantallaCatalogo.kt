package com.example.stockpro

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantallaCatalogo(
    nombreOperario: String,
    viewModel: StockViewModel,
    onProductoClick: (Int) -> Unit,
    onReporteClick: () -> Unit
) {
    var mostrarCritico by remember { mutableStateOf(false) }

    val listaAMostrar = if (mostrarCritico)
        viewModel.obtenerProductosEnRiesgo()
    else
        viewModel.productos

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onReporteClick,
                containerColor = LilaPrincipal
            ) {
                Icon(Icons.Default.Assessment, contentDescription = "Reporte", tint = Color.White)
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {

            // Encabezado
            Surface(color = LilaClaro, modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Operario: $nombreOperario",
                        fontWeight = FontWeight.Bold,
                        color = LilaOscuro,
                        fontSize = 16.sp)
                    Text("Inventario general", color = Color.Gray, fontSize = 13.sp)
                }
            }

            // Botones de filtro
            Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                FilterChip(
                    selected = !mostrarCritico,
                    onClick = { mostrarCritico = false },
                    label = { Text("Ver Todo") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = LilaPrincipal,
                        selectedLabelColor = Color.White
                    )
                )
                FilterChip(
                    selected = mostrarCritico,
                    onClick = { mostrarCritico = true },
                    label = { Text("Stock Crítico") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFFDC2626),
                        selectedLabelColor = Color.White
                    )
                )
            }

            // Lista
            LazyColumn(contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(listaAMostrar) { producto ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onProductoClick(producto.id) },
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Row(modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(producto.nombre, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                                Text("$${producto.precio}", color = Color.Gray, fontSize = 13.sp)
                            }
                            Text(
                                text = "Stock: ${producto.stockActual}",
                                color = if (producto.stockActual < 5) Color.Red else LilaPrincipal,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}