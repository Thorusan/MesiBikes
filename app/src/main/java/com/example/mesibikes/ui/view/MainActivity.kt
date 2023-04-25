package com.example.mesibikes.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.coroutineScope
import com.example.mesibikes.model.bikesList
import com.example.mesibikes.ui.theme.MesiBikesTheme
import com.example.mesibikes.vm.BikeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val bikeViewModel by viewModel<BikeViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addBikes()

        setContent {
            MesiBikesTheme {
                BikeScreen(
                    bikes = bikeViewModel.bikes.collectAsState().value,
                    onAddBike = {

                })
            }
        }
    }

    private fun addBikes() {
        lifecycle.coroutineScope.launch {
            bikesList.forEach {
                bikeViewModel.addBike(it)
            }
        }
    }
}

