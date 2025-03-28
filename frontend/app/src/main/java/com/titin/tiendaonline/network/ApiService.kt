package com.titin.tiendaonline.network

import com.titin.tiendaonline.network.response.ProductoResponse
import retrofit2.Response
import retrofit2.http.GET

// Definimos una interfaz que representa las solicitudes a la API
interface APIService {

    // Método para obtener la lista de productos desde el servidor
    @GET("productos") // Indica que esta solicitud es de tipo GET y apunta al endpoint "productos"
    suspend fun obtenerProductos(): Response<ProductoResponse>
    // La función es 'suspend' porque se ejecutará en una corutina (para operaciones asíncronas).
    // Retorna un objeto Response que contiene un ProductoResponse (la respuesta de la API).
}
