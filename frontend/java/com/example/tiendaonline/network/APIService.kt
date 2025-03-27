package com.example.tiendaonline.network

import com.example.tiendaonline.network.response.ProductoResponse
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("productos")
    suspend fun obtenerProductos(): Response<ProductoResponse>
}