package com.example.stockpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val stockViewModel: StockViewModel = viewModel()

            NavHost(
                navController = navController,
                startDestination = "login"
            ) {
                composable("login") {
                    PantallaLogin(
                        onIngresar = { nombre ->
                            navController.navigate("catalogo/$nombre")
                        }
                    )
                }

                composable(
                    route = "catalogo/{nombre}",
                    arguments = listOf(navArgument("nombre") { type = NavType.StringType })
                ) { backStackEntry ->
                    val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
                    PantallaCatalogo(
                        nombreOperario = nombre,
                        viewModel = stockViewModel,
                        onProductoClick = { id ->
                            navController.navigate("edicion/$id")
                        },
                        onReporteClick = {
                            navController.navigate("reporte")
                        }
                    )
                }

                composable(
                    route = "edicion/{productoId}",
                    arguments = listOf(navArgument("productoId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val id = backStackEntry.arguments?.getInt("productoId") ?: 0
                    PantallaEdicion(
                        productoId = id,
                        viewModel = stockViewModel,
                        onVolver = {
                            navController.popBackStack()
                        }
                    )
                }

                composable("reporte") {
                    PantallaReporte(
                        viewModel = stockViewModel,
                        onVolver = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}