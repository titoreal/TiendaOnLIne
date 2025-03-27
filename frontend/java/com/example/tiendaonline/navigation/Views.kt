package com.example.tiendaonline.navigation

sealed class Views(val route: String) {
    object ProductosScreen : Views("productos_screen")
    object CarritoScreen : Views("carrito_screen")
    object TicketScreen : Views("ticket_screen")
}