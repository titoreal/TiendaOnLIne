package com.titin.tiendaonline.models

data class Producto(
    val numero_serie: String,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val imagen: String? = null,
    val imagen_url: String? = null
)