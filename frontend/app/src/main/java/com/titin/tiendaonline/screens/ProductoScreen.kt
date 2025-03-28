package com.titin.tiendaonline.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.titin.tiendaonline.R
import com.titin.tiendaonline.viewmodels.MainViewModel
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ProductosScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    val listaProductos by viewModel.listaProductos.collectAsState()
    val listaCarrito by viewModel.listaCarrito.collectAsState()
    val badgeCount by viewModel.badgeContador.collectAsState()

    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.primaryContainer
                            )
                        )
                    )
                    .padding(top = 40.dp)  // Mantener padding para barra de estado
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Logo y Nombre con efecto de sombra
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Box(
                            modifier = Modifier
                                .shadow(8.dp, shape = CircleShape)
                                .background(Color.White, shape = CircleShape)
                                .padding(8.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.logo),
                                contentDescription = "Computin Logo",
                                modifier = Modifier.size(48.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = "Computin PC  y más",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,  // Correcto
                            color = Color.Blue,
                            modifier = Modifier
                                .shadow(4.dp)
                        )
                    }

                    // Carrito con contador
                    BadgedBox(
                        badge = {
                            if (badgeCount > 0) {
                                Badge(
                                    containerColor = MaterialTheme.colorScheme.error,
                                    contentColor = Color.White
                                ) {
                                    Text(
                                        text = badgeCount.toString(),
                                        style = MaterialTheme.typography.labelSmall
                                    )
                                }
                            }
                        }
                    ) {
                        IconButton(
                            onClick = {
                                if (listaCarrito.isNotEmpty()) {
                                    navController.navigate("carrito_screen")
                                } else {
                                    Toast.makeText(
                                        context,
                                        "NECESITAS AGREGAR ARTICULOS AL CARRITO",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.car_shop),
                                contentDescription = "carro compras",
                                tint = Color.Black.copy(alpha = 0.9f),  // Asegurar visibilidad
                                modifier = Modifier
                                    .size(32.dp)
                                    .scale(if (badgeCount > 0) 1.1f else 1f)
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.surfaceVariant
                        )
                    )
                ),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(listaProductos) { producto ->
                // El resto del código de la cuadrícula de productos permanece igual
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .clickable { /* Acción de detalle del producto */ }
                        .graphicsLayer {
                            shadowElevation = 8.dp.toPx()
                            shape = RoundedCornerShape(16.dp)
                            clip = true
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    ),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    // Contenido de la tarjeta (igual que en la versión anterior)
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp)
                    ) {
                        // Imagen del producto con efecto
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(
                                            Color.White,
                                            MaterialTheme.colorScheme.surfaceVariant
                                        )
                                    ),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                                    shape = RoundedCornerShape(12.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (!producto.imagen_url.isNullOrEmpty()) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(producto.imagen_url)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = "imagen de ${producto.nombre}",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .scale(0.8f),
                                    contentScale = ContentScale.Fit,
                                    placeholder = painterResource(R.drawable.producto),
                                    error = painterResource(R.drawable.producto)
                                )
                            } else {
                                Image(
                                    painter = painterResource(R.drawable.producto),
                                    contentDescription = "imagen de ${producto.nombre}",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .scale(0.8f),
                                    contentScale = ContentScale.Fit
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Nombre del producto
                        Text(
                            text = producto.nombre,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        // Descripción
                        Text(
                            text = producto.descripcion,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(vertical = 4.dp),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                        // Precio y botón
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "$${String.format("%.2f", producto.precio)}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )

                            FilledTonalButton(
                                onClick = {
                                    viewModel.agregarCarrito(producto)
                                    // Pequeño feedback visual
                                },
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.size(48.dp),
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.add_car_shop),
                                    contentDescription = "Agregar al carrito",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}