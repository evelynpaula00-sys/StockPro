package com.example.stockpro

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class StockViewModel : ViewModel() {

    val productos = mutableStateListOf(
        Producto(1, "Cemento 50kg", "Bolsa de cemento gris", 8.50, 12),
        Producto(2, "Pintura blanca", "Galón pintura interior", 15.00, 3),
        Producto(3, "Tornillos 3\"", "Caja 100 unidades acero", 2.75, 0),
        Producto(4, "Manguera 10m", "Manguera reforzada verde", 12.00, 7),
        Producto(5, "Cable eléctrico", "Rollo 50m calibre 12", 22.00, 4),
        Producto(6, "Lija #120", "Pliego lija para madera", 0.80, 25)
    )

    fun obtenerProducto(id: Int): Producto? =
        productos.find { it.id == id }

    fun actualizarStock(id: Int, nuevaCantidad: Int) {
        val index = productos.indexOfFirst { it.id == id }
        if (index != -1) {
            productos[index] = productos[index].copy(stockActual = nuevaCantidad)
        }
    }

    fun calcularValorTotalInventario(): Double =
        productos.sumOf { it.precio * it.stockActual }

    fun obtenerProductosEnRiesgo(): List<Producto> =
        productos.filter { it.stockActual < 5 }

    fun obtenerProductosEnCero(): Int =
        productos.count { it.stockActual == 0 }
}