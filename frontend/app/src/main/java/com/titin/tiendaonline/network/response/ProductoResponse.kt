package com.titin.tiendaonline.network.response

import com.titin.tiendaonline.models.Producto

// Definimos una data class que representa la respuesta de la API cuando se obtienen productos
data class ProductoResponse(
    val codigo: String = "200", // Código de estado de la respuesta (ejemplo: "200" para éxito)
    val mensaje: String = "", // Mensaje opcional de la API (puede ser un error o confirmación)
    val productos: List<Producto> = emptyList() // Lista de productos, por defecto vacía
)
