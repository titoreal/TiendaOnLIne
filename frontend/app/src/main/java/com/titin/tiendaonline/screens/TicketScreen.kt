package com.titin.tiendaonline.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.titin.tiendaonline.viewmodels.MainViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import com.titin.tiendaonline.R

@Composable
fun TicketScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    // Obtener la lista actual del carrito
    val listaCarrito by viewModel.listaCarrito.collectAsState()

    // Calcular los valores necesarios
    val totalCarrito = remember(listaCarrito) {
        listaCarrito.sumOf { it.cantidad * it.precio }
    }
    val ivaRate = 0.15
    val subtotal = remember(totalCarrito) { totalCarrito / (1 + ivaRate) }
    val ivaAmount = remember(totalCarrito, subtotal) { totalCarrito - subtotal }

    // Formatear la fecha actual
    val currentDate = remember {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        dateFormat.format(Date())
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(vertical = 16.dp, horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "Computin Logo",
                    modifier = Modifier
                        .size(48.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = "Computin",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Contenedor del ticket
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
                    .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    // Encabezado
                    Text(
                        text = "COMPUTIN",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "RECIBO DE COMPRA",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Fecha y hora
                    Text(
                        text = "Fecha: $currentDate",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        fontSize = 12.sp
                    )

                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        color = Color.Black,
                        thickness = 1.dp
                    )

                    // Encabezado de items
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Producto", fontWeight = FontWeight.Bold)
                        Text("Cant.", fontWeight = FontWeight.Bold)
                        Text("Precio", fontWeight = FontWeight.Bold)
                        Text("Total", fontWeight = FontWeight.Bold)
                    }

                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        color = Color.Black,
                        thickness = 0.5.dp
                    )

                    // Lista de items
                    listaCarrito.forEach { producto ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = producto.nombre,
                                modifier = Modifier.weight(0.4f),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "${producto.cantidad}",
                                modifier = Modifier.weight(0.2f),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "$${String.format("%.2f", producto.precio)}",
                                modifier = Modifier.weight(0.2f),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "$${String.format("%.2f", producto.cantidad * producto.precio)}",
                                modifier = Modifier.weight(0.2f),
                                textAlign = TextAlign.Right
                            )
                        }
                    }

                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        color = Color.Black,
                        thickness = 1.dp
                    )

                    // Totales
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Subtotal:", fontWeight = FontWeight.Bold)
                        Text("$${String.format("%.2f", subtotal)}")
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("IVA (15%):", fontWeight = FontWeight.Bold)
                        Text("$${String.format("%.2f", ivaAmount)}")
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Total:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text(
                            "$${String.format("%.2f", totalCarrito)}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Mensaje de agradecimiento
                    Text(
                        text = "¡Gracias por su compra!",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic
                    )
                }
            }

            // Botones de Imprimir y Nueva Compra
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        // Simular impresión
                        Toast.makeText(
                            navController.context,
                            "Imprimiendo ticket...",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    modifier = Modifier.width(150.dp)
                ) {
                    Text("Imprimir")
                }

                Button(
                    onClick = {
                        // Navegar de vuelta a productos y limpiar carrito
                        viewModel.limpiarCarrito()
                        navController.navigate("productos_screen") {
                            popUpTo("productos_screen") { inclusive = true }
                        }
                    },
                    modifier = Modifier.width(150.dp)
                ) {
                    Text("Nueva Compra")
                }
            }
        }
    }
}