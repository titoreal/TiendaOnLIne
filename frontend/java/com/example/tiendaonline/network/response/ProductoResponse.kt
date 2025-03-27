package com.example.tiendaonline.network.response

import com.example.tiendaonline.models.Producto

data class ProductoResponse(
    val codigo: String = "200",
    val mensaje: String = "",
    val productos: List<Producto> = emptyList()
)