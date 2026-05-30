package com.example.pc1movileslira24100302wu24100803.presentation.PlanificacionPresupuestoViaje

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanificarPresupuestoDeViajeScreen() {
    // Estados para los campos de entrada
    var diasStr by remember { mutableStateOf("") }
    var presupuestoDiarioStr by remember { mutableStateOf("") }
    
    // Opciones de alojamiento y sus factores
    val tiposAlojamiento = listOf(
        "Económico" to 0.8,
        "Estándar" to 1.0,
        "Premium" to 1.5
    )
    
    var expanded by remember { mutableStateOf(false) }
    var alojamientoSeleccionado by remember { mutableStateOf(tiposAlojamiento[1]) } // Estándar por defecto

    // Estados para los resultados y mensajes de error
    var presupuestoTotal by remember { mutableStateOf<Double?>(null) }
    var mensajeEscenario by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "PLANIFICADOR DE PRESUPUESTO",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Campo: Cantidad de días
        OutlinedTextField(
            value = diasStr,
            onValueChange = { 
                diasStr = it
                errorMsg = ""
            },
            label = { Text("Cantidad de días") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            isError = errorMsg.contains("días") || (errorMsg.isNotEmpty() && diasStr.isBlank()),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo: Presupuesto diario
        OutlinedTextField(
            value = presupuestoDiarioStr,
            onValueChange = { 
                presupuestoDiarioStr = it
                errorMsg = ""
            },
            label = { Text("Presupuesto diario") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth(),
            isError = errorMsg.contains("presupuesto") || (errorMsg.isNotEmpty() && presupuestoDiarioStr.isBlank()),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Selector: Tipo de alojamiento (DropdownMenu)
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = alojamientoSeleccionado.first,
                onValueChange = {},
                readOnly = true,
                label = { Text("Tipo de alojamiento") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                tiposAlojamiento.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion.first) },
                        onClick = {
                            alojamientoSeleccionado = opcion
                            expanded = false
                        }
                    )
                }
            }
        }

        if (errorMsg.isNotEmpty()) {
            Text(
                text = errorMsg,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp).align(Alignment.Start)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón: Calcular
        Button(
            onClick = {
                val dias = diasStr.toIntOrNull()
                val presupuestoDiario = presupuestoDiarioStr.toDoubleOrNull()

                // Validaciones
                if (diasStr.isBlank() || presupuestoDiarioStr.isBlank()) {
                    errorMsg = "Todos los campos son obligatorios"
                    presupuestoTotal = null
                } else if (dias == null || dias <= 0) {
                    errorMsg = "La cantidad de días debe ser mayor a cero"
                    presupuestoTotal = null
                } else if (presupuestoDiario == null || presupuestoDiario <= 0) {
                    errorMsg = "El presupuesto diario debe ser mayor a cero"
                    presupuestoTotal = null
                } else {
                    errorMsg = ""
                    // Fórmula: total = días × presupuesto diario × factor
                    val total = dias * presupuestoDiario * alojamientoSeleccionado.second
                    presupuestoTotal = total
                    mensajeEscenario = "Presupuesto calculado para un viaje de $dias días con alojamiento ${alojamientoSeleccionado.first.lowercase()}."
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("CALCULAR PRESUPUESTO")
        }

        // Mostrar resultados
        presupuestoTotal?.let { total ->
            Spacer(modifier = Modifier.height(32.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE3F2FD)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Presupuesto Total:",
                        style = MaterialTheme.typography.labelLarge,
                        color = Color(0xFF1565C0)
                    )
                    Text(
                        text = "$ ${"%.2f".format(total)}",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0D47A1)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = mensajeEscenario,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF0D47A1)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlanificarPresupuestoDeViajePreview() {
    PlanificarPresupuestoDeViajeScreen()
}
