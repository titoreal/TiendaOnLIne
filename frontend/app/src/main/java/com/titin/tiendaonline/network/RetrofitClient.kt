package com.titin.tiendaonline.network

import com.titin.tiendaonline.utils.Constantes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Objeto Singleton que maneja la instancia de Retrofit
object RetrofitClient {

    // Creamos una instancia de APIService utilizando Retrofit
    val retrofit: APIService by lazy {
        Retrofit.Builder() // Inicia la configuración de Retrofit
            .baseUrl(Constantes.BASE_URL) // Define la URL base de la API
            .addConverterFactory(GsonConverterFactory.create()) // Convierte JSON automáticamente a objetos de Kotlin usando Gson
            .build() // Construye la instancia de Retrofit
            .create(APIService::class.java) // Crea una implementación de la interfaz APIService
    }
}
