package com.example.tiendaonline.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tiendaonline.screens.CarritoScreen
import com.example.tiendaonline.screens.ProductosScreen
import com.example.tiendaonline.screens.TicketScreen
import com.example.tiendaonline.viewmodels.MainViewModel



@Composable
fun NavManager(paddingValues: PaddingValues) {
    val navController = rememberNavController()
    val viewModel: MainViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Views.ProductosScreen.route
    ) {
        composable(Views.ProductosScreen.route) {
            ProductosScreen(
                paddingValues = paddingValues,
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(Views.CarritoScreen.route) {
            CarritoScreen(
                paddingValues = paddingValues,
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(Views.TicketScreen.route) {
            TicketScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}
