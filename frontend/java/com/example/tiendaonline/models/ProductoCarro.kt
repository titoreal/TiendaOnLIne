package com.example.tiendaonline
// Modifica la clase ProductoCarro para permitir que imagen e imagen_url sean nulos
data class ProductoCarro(
    val numero_serie: String,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val imagen: String? = null,
    val imagen_url: String? = null,
    val cantidad: Int = 1
)