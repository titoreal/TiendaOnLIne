package com.titin.tiendaonline.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.titin.tiendaonline.screens.CarritoScreen
import com.titin.tiendaonline.screens.ProductosScreen
import com.titin.tiendaonline.screens.TicketScreen
import com.titin.tiendaonline.viewmodels.MainViewModel
// Esta función maneja la navegación entre pantallas en Jetpack Compose.
@Composable
fun NavManager(paddingValues: PaddingValues) {

    // Creamos un controlador de navegación que nos permitirá movernos entre pantallas.
    val navController = rememberNavController()

    // Obtenemos una instancia del ViewModel principal para compartir datos entre pantallas.
    val viewModel: MainViewModel = viewModel()

    // Definimos el contenedor de navegación que manejará las pantallas.
    NavHost(
        navController = navController,  // Pasamos el controlador de navegación.
        startDestination = Views.ProductosScreen.route // Definimos la pantalla inicial.
    ) {
        // Definimos la ruta para la pantalla de productos.
        composable(Views.ProductosScreen.route) {
            ProductosScreen(
                paddingValues = paddingValues, // Espaciado que proporciona el scaffold.
                navController = navController, // Pasamos el controlador de navegación.
                viewModel = viewModel // Pasamos el ViewModel para obtener datos.
            )
        }

        // Definimos la ruta para la pantalla del carrito.
        composable(Views.CarritoScreen.route) {
            CarritoScreen(
                paddingValues = paddingValues,
                navController = navController,
                viewModel = viewModel
            )
        }

        // Definimos la ruta para la pantalla del ticket (confirmación de compra).
        composable(Views.TicketScreen.route) {
            TicketScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

