package com.example.pc1movileslira24100302wu24100803

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pc1movileslira24100302wu24100803.presentation.AsistenciaViaje.AsistenciaViajeScreen
import com.example.pc1movileslira24100302wu24100803.presentation.CatalogDestinos.CatalogDestinosScreen
import com.example.pc1movileslira24100302wu24100803.ui.theme.PC1MOVILESLIRA24100302WU24100803Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PC1MOVILESLIRA24100302WU24100803Theme {

                AsistenciaViajeScreen()

            }
        }
    }
}
