package com.titin.tiendaonline.screens

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.request.ImageRequest
import com.titin.tiendaonline.viewmodels.MainViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ArrowBack
import coil.compose.AsyncImage
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.titin.tiendaonline.R
// Composable para la pantalla del carrito de compras
@Composable
fun CarritoScreen(
    paddingValues: PaddingValues,  // Valores de padding para la pantalla
    navController: NavController,  // Controlador de navegación para navegar entre pantallas
    viewModel: MainViewModel  // ViewModel que contiene la lógica del carrito
) {
    // Lista de productos en el carrito
    val listaCarrito by viewModel.listaCarrito.collectAsState()

    // Cálculo del total del carrito
    val totalCarrito by remember {
        derivedStateOf {
            listaCarrito.sumOf { it.cantidad * it.precio }
        }
    }

    // Contexto para mostrar mensajes
    val context = LocalContext.current

    // Contenedor principal de la pantalla (Box)
    Box(
        modifier = Modifier
            .fillMaxSize()  // Ocupa todo el espacio disponible
            .padding(paddingValues)  // Aplica los valores de padding
    ) {
        // Columna que contiene todo el contenido de la pantalla
        Column(
            modifier = Modifier
                .fillMaxSize()  // Ocupa todo el espacio disponible
                .padding(bottom = 50.dp)  // Añade un padding inferior
        ) {
            // Fila para el botón de retorno y el título
            Row(
                modifier = Modifier
                    .fillMaxWidth()  // Ocupa el ancho completo
                    .padding(8.dp),  // Aplica padding de 8dp
                verticalAlignment = Alignment.CenterVertically  // Alinea verticalmente al centro
            ) {
                // Botón para regresar a la pantalla anterior
                IconButton(
                    onClick = { navController.navigateUp() },  // Vuelve a la pantalla anterior
                    modifier = Modifier.size(32.dp)  // Define el tamaño del icono
                ) {
                    // Icono de flecha hacia atrás
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver a productos"
                    )
                }

                // Título de la pantalla "CARRITO DE COMPRAS"
                Text(
                    modifier = Modifier
                        .weight(1f)  // Toma el resto del espacio disponible
                        .padding(start = 8.dp),  // Padding a la izquierda
                    text = "CARRITO DE COMPRAS",
                    textAlign = TextAlign.Center,  // Alinea el texto al centro
                    fontSize = 24.sp,  // Tamaño de fuente
                    fontWeight = FontWeight.Bold  // Texto en negrita
                )
            }

            // Fila con los encabezados de las columnas del carrito
            Row(
                modifier = Modifier
                    .fillMaxWidth()  // Ocupa el ancho completo
                    .padding(horizontal = 4.dp, vertical = 2.dp)  // Padding alrededor
            ) {
                // Encabezado de "PRODUCTO"
                Text(
                    modifier = Modifier.weight(2f),  // Toma 2/4 del espacio
                    text = "PRODUCTO",
                    textAlign = TextAlign.Center,  // Alineado al centro
                    fontWeight = FontWeight.SemiBold  // Negrita, pero no tan intensa
                )

                // Encabezado de "CANTIDAD"
                Text(
                    modifier = Modifier.weight(1f),  // Toma 1/4 del espacio
                    text = "CANTIDAD",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold
                )

                // Encabezado de "PRECIO"
                Text(
                    modifier = Modifier.weight(1f),  // Toma 1/4 del espacio
                    text = "PRECIO",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // Línea divisoria
            HorizontalDivider()

            // Lista de productos en el carrito
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 8.dp)  // Padding horizontal
                    .weight(1f),  // Toma el resto del espacio disponible
                verticalArrangement = Arrangement.Top,  // Arreglo en la parte superior
                contentPadding = PaddingValues(vertical = 2.dp)  // Padding vertical
            ) {
                // Recorre la lista de productos y crea una fila por cada uno
                items(listaCarrito) { producto ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()  // Ocupa todo el ancho
                            .padding(vertical = 4.dp),  // Padding vertical
                        verticalAlignment = Alignment.CenterVertically  // Alineación vertical
                    ) {
                        // Producto y su imagen
                        Row(
                            modifier = Modifier.weight(2f),  // Ocupa 2/4 del espacio
                            verticalAlignment = Alignment.CenterVertically  // Alinea verticalmente al centro
                        ) {
                            // Verifica si tiene una imagen URL
                            if (!producto.imagen_url.isNullOrEmpty()) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(producto.imagen_url)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = "imagen de ${producto.nombre}",
                                    modifier = Modifier
                                        .size(40.dp)  // Tamaño de la imagen
                                        .padding(end = 8.dp),  // Padding a la derecha
                                    contentScale = ContentScale.Fit,
                                    placeholder = painterResource(R.drawable.producto),
                                    error = painterResource(R.drawable.producto)
                                )
                            } else {
                                // Si no tiene imagen, muestra una predeterminada
                                Image(
                                    painter = painterResource(id = R.drawable.producto),
                                    contentDescription = "imagen de ${producto.nombre}",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(end = 8.dp)
                                )
                            }

                            Text(
                                text = producto.nombre,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 12.sp
                            )
                        }

                        // Controles de cantidad
                        Row(
                            modifier = Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            IconButton(
                                onClick = { viewModel.decrementarCantidad(producto) },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Remove,
                                    contentDescription = "Disminuir cantidad"
                                )
                            }

                            Text(
                                text = "${producto.cantidad}",
                                modifier = Modifier.padding(horizontal = 8.dp),
                                fontSize = 14.sp
                            )

                            IconButton(
                                onClick = { viewModel.incrementarCantidad(producto) },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Aumentar cantidad"
                                )
                            }

                            // Botón para eliminar
                            IconButton(
                                onClick = { viewModel.eliminarProductoCarrito(producto) },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Eliminar producto",
                                    tint = Color.Red
                                )
                            }
                        }

                        // Precio total del producto
                        Text(
                            modifier = Modifier.weight(1f),
                            text = "$${String.format("%.2f", producto.precio * producto.cantidad)}",
                            textAlign = TextAlign.Center,
                            fontSize = 12.sp
                        )
                    }

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 1.dp),
                        thickness = 0.5.dp,
                        color = Color.LightGray
                    )
                }
            }

            HorizontalDivider()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Total: $${String.format("%.2f", totalCarrito)}",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }


            // New Checkout Button
            Button(
                onClick = {
                    if (listaCarrito.isNotEmpty()) {
                        // Navigate to payment screen or show payment dialog
                        showCheckoutDialog(context, totalCarrito, viewModel, navController)
                    } else {
                        Toast.makeText(
                            context,
                            "El carrito está vacío",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Proceder al Pago")
            }
        }
    }
}

// Función para mostrar un diálogo de confirmación de compra
fun showCheckoutDialog(
    context: Context,
    total: Double,
    viewModel: MainViewModel,
    navController: NavController
) {
    AlertDialog.Builder(context)
        .setTitle("Confirmar Compra")
        .setMessage("Total a pagar: $${String.format("%.2f", total)}\n\n¿Desea confirmar la compra?")
        .setPositiveButton("Confirmar") { dialog, _ ->
            // Solo navega al ticket
            navController.navigate("ticket_screen") {
                popUpTo("productos_screen")
            }

            Toast.makeText(
                context,
                "Compra realizada con éxito",
                Toast.LENGTH_LONG
            ).show()
            dialog.dismiss()
        }
        .setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }
        .create()
        .show()
}





































































































