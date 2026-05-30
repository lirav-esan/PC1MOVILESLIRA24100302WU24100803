package com.example.pc1movileslira24100302wu24100803.presentation.CalcEquipaje

import androidx.compose.foundation.clickable
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

// Andres Alfredo Wu Solano - 24100803
@Composable
fun CalcularEquipajeScreen() {
    var pesoInput by remember { mutableStateOf("") }
    var tipoVuelo by remember { mutableStateOf("Nacional") }
    var resultado by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    var esValido by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "CALCULADORA DE EQUIPAJE",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = pesoInput,
            onValueChange = {
                pesoInput = it
                error = ""
            },
            label = { Text("Peso de la maleta (kg)") },
            placeholder = { Text("Ej: 25.5") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth(),
            isError = error.isNotEmpty(),
            singleLine = true
        )

        if (error.isNotEmpty()) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 4.dp, top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Tipo de vuelo:",
            modifier = Modifier.align(Alignment.Start),
            fontWeight = FontWeight.Medium
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = tipoVuelo == "Nacional",
                onClick = { tipoVuelo = "Nacional" }
            )
            Text(
                text = "Nacional (Máx 23kg)",
                modifier = Modifier.clickable { tipoVuelo = "Nacional" }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = tipoVuelo == "Internacional",
                onClick = { tipoVuelo = "Internacional" }
            )
            Text(
                text = "Internacional (Máx 32kg)",
                modifier = Modifier.clickable { tipoVuelo = "Internacional" }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val peso = pesoInput.toDoubleOrNull()

                if (pesoInput.isBlank()) {
                    error = "Campo obligatorio"
                    resultado = ""
                } else if (peso == null) {
                    error = "Valor numérico inválido"
                    resultado = ""
                } else if (peso <= 0) {
                    error = "Debe ser mayor a cero"
                    resultado = ""
                } else {
                    error = ""
                    val limite = if (tipoVuelo == "Nacional") 23.0 else 32.0
                    if (peso <= limite) {
                        resultado = "CUMPLE EL LÍMITE PERMITIDO"
                        esValido = true
                    } else {
                        val excedido = peso - limite
                        resultado = "EXCEDE EL LÍMITE PERMITIDO\nCantidad de kg excedidos: ${"%.2f".format(excedido)} kg"
                        esValido = false
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("CALCULAR")
        }

        if (resultado.isNotEmpty()) {
            Spacer(modifier = Modifier.height(32.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = if (esValido) Color(0xFFE8F5E9) else Color(0xFFFFEBEE)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = resultado,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = if (esValido) Color(0xFF2E7D32) else Color(0xFFC62828)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalcularEquipajePreview() {
    CalcularEquipajeScreen()
}
