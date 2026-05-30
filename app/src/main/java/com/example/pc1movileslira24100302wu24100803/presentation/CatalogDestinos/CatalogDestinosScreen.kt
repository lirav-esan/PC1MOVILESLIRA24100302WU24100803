package com.example.pc1movileslira24100302wu24100803.presentation.CatalogDestinos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
// Fabriccio Alonso Lira Vera - 24100302
@Composable
fun CatalogDestinosScreen() {
    val destinations = listOf(
        Destination(
            country = "España",
            city = "Barcelona",
            averageCost = 1200.0,
            imageUrl = "https://flagcdn.com/256x192/es.png"
        ),
        Destination(
            country = "Francia",
            city = "París",
            averageCost = 1500.0,
            imageUrl = "https://flagcdn.com/256x192/fr.png"
        ),
        Destination(
            country = "Italia",
            city = "Roma",
            averageCost = 1100.0,
            imageUrl = "https://flagcdn.com/256x192/it.png"
        ),
        Destination(
            country = "Alemania",
            city = "Berlín",
            averageCost = 950.0,
            imageUrl = "https://flagcdn.com/256x192/de.png"
        ),
        Destination(
            country = "Portugal",
            city = "Lisboa",
            averageCost = 800.0,
            imageUrl = "https://flagcdn.com/256x192/pt.png"
        ),
        Destination(
            country = "Grecia",
            city = "Atenas",
            averageCost = 900.0,
            imageUrl = "https://flagcdn.com/256x192/gr.png"
        )
    )

    val totalDestinations = destinations.size
    val totalCost = destinations.sumOf { it.averageCost }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "Catalogo de destinos turísticos",
                modifier = Modifier.padding(top=48.dp, bottom = 16.dp),
                fontSize = 24.dp.value.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
        }

        items(destinations) { destination ->
            DestinationCard(destination)
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            ) {
                Text(text = "Cantidad total de destinos: $totalDestinations")
                Text(text = "Suma acumulada de costos: $${"%.2f".format(totalCost)}")
            }
        }
    }
}

@Composable
fun DestinationCard(destination: Destination) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = destination.imageUrl,
                contentDescription = "${destination.city}, ${destination.country}",
                modifier = Modifier.size(100.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "País: ${destination.country}")
                Text(text = "Ciudad: ${destination.city}")
                Text(text = "Costo promedio: $${destination.averageCost}")
            }
        }
    }
}
