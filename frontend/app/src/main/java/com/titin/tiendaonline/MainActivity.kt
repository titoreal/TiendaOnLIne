package com.titin.tiendaonline

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.titin.tiendaonline.navigation.NavManager
import com.titin.tiendaonline.ui.theme.TiendaOnLine

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TiendaOnLine {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    NavManager(innerPadding)

                }
            }
        }
    }
}
