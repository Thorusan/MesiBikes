package com.example.mesibikes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mesibikes.db.Bike
import com.example.mesibikes.ui.theme.MesiBikesTheme

@Composable
fun BikeScreen(
    bikes: List<Bike>,
    onAddBike: (bike: Bike) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(bikes) { bike ->
            ItemBike(bike)


            Divider(modifier = Modifier.padding(top = 16.dp))
        }
    }
}


@Composable
fun ItemBike(bike: Bike) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .padding(start = 8.dp, top = 12.dp)
                .align(Alignment.CenterStart),
            text = bike.name
        )

        Text(
            modifier = Modifier
                .padding(start = 8.dp, top = 12.dp)
                .align(Alignment.CenterEnd),
            text = bike.status.name
        )
    }
}

@Preview(device = Devices.PIXEL_4, showBackground = true, showSystemUi = true)
@Composable
fun OrderScreenPreview() {
    MesiBikesTheme {
        BikeScreen(
            bikesList,

            ) {}
    }
}
