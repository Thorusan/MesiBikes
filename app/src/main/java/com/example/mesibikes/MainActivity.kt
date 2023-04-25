package com.example.mesibikes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.coroutineScope
import com.example.mesibikes.db.Bike
import com.example.mesibikes.db.BikeStatus
import com.example.mesibikes.ui.theme.MesiBikesTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val bikeViewModel by viewModel<BikeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addBike(Bike("BAJK 1", BikeStatus.AVAILABLE))
        addBike(Bike("BAJK 2", BikeStatus.AVAILABLE))
        addBike(Bike("BAJK 3", BikeStatus.AVAILABLE))
        addBike(Bike("BAJK 4", BikeStatus.AVAILABLE))
        addBike(Bike("BAJK 5", BikeStatus.AVAILABLE))
        addBike(Bike("BAJK 6", BikeStatus.AVAILABLE))
        addBike(Bike("BAJK 7", BikeStatus.AVAILABLE))


        setContent {
            val bikes = bikeViewModel.bikes.collectAsState().value

            MesiBikesTheme {
                BikeScreen()
            }
        }
    }

    private fun addBike(bike: Bike) {
        lifecycle.coroutineScope.launch {
            bikeViewModel.addBike(bike)
        }
    }
}

