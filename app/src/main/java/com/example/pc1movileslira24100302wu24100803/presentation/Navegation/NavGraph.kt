package com.example.pc1movileslira24100302wu24100803.presentation.Navegation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pc1movileslira24100302wu24100803.presentation.AsistenciaViaje.AsistenciaViajeScreen
import com.example.pc1movileslira24100302wu24100803.presentation.CalcEquipaje.CalcularEquipajeScreen
import com.example.pc1movileslira24100302wu24100803.presentation.CatalogDestinos.CatalogDestinosScreen
import com.example.pc1movileslira24100302wu24100803.presentation.PlanificacionPresupuestoViaje.PlanificarPresupuestoDeViajeScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "equipaje"
    ) {
        composable("equipaje") {
            DrawerScaffold(navController) {
                CalcularEquipajeScreen()
            }
        }
        composable("presupuesto") {
            DrawerScaffold(navController) {
                PlanificarPresupuestoDeViajeScreen()
            }
        }
        composable("catalogo") {
            DrawerScaffold(navController) {
                CatalogDestinosScreen()
            }
        }
        composable("asistencia") {
            DrawerScaffold(navController) {
                AsistenciaViajeScreen()
            }
        }
    }
}
