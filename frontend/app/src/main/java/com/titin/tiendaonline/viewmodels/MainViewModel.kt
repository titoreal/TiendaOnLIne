package com.titin.tiendaonline.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.titin.tiendaonline.models.Producto
import com.titin.tiendaonline.models.ProductoCarro
import com.titin.tiendaonline.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _listaProductos = MutableStateFlow<List<Producto>>(emptyList())
    val listaProductos = _listaProductos.asStateFlow()

    private val _listaCarrito = MutableStateFlow<List<ProductoCarro>>(emptyList())
    val listaCarrito = _listaCarrito.asStateFlow()

    val badgeContador = _listaCarrito.map { carrito ->
        carrito.sumOf { it.cantidad }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0)

    init {
        obtenerProductos()
    }

    fun obtenerProductos() = viewModelScope.launch {
        try {
            val response = RetrofitClient.retrofit.obtenerProductos()
            _listaProductos.value = response.body()?.productos ?: emptyList()
        } catch (e: Exception) {
            Log.e("MainViewModel", "Error al obtener productos", e)
        }
    }

    fun agregarCarrito(producto: Producto) {
        _listaCarrito.update { carrito ->
            val existingProducto = carrito.find { it.numero_serie == producto.numero_serie }
            existingProducto?.let {
                carrito.map {
                    if (it.numero_serie == producto.numero_serie)
                        it.copy(cantidad = it.cantidad + 1)
                    else it
                }
            } ?: (carrito + ProductoCarro(
                numero_serie = producto.numero_serie,
                nombre = producto.nombre,
                descripcion = producto.descripcion,
                precio = producto.precio,
                imagen = producto.imagen,
                imagen_url = producto.imagen_url
            ))
        }
    }

    fun incrementarCantidad(producto: ProductoCarro) {
        _listaCarrito.update { carrito ->
            carrito.map {
                if (it.numero_serie == producto.numero_serie)
                    it.copy(cantidad = it.cantidad + 1)
                else it
            }
        }
    }

    fun decrementarCantidad(producto: ProductoCarro) {
        _listaCarrito.update { carrito ->
            carrito.mapNotNull {
                if (it.numero_serie == producto.numero_serie) {
                    if (it.cantidad > 1) it.copy(cantidad = it.cantidad - 1)
                    else null
                } else it
            }
        }
    }

    fun eliminarProductoCarrito(producto: ProductoCarro) {
        _listaCarrito.update { carrito ->
            carrito.filter { it.numero_serie != producto.numero_serie }
        }
    }

    fun limpiarCarrito() {
        _listaCarrito.value = emptyList()
    }
}