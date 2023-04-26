package com.example.mesibikes.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.coroutineScope
import com.example.mesibikes.ui.theme.MesiBikesTheme
import com.example.mesibikes.vm.BikeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<BikeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MesiBikesTheme {
                BikeScreen(
                    bikes = viewModel.bikes.collectAsState().value,
                    onAddBike = { bike, user ->
                        lifecycle.coroutineScope.launch {
                            viewModel.addReservation(bike, user)
                        }
                    }
                )
            }
        }
    }
}

