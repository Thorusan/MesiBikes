package com.example.mesibikes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mesibikes.db.Bike
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bikeViewModelModule = module {
    viewModel {
        BikeViewModel(get())
    }
}

class BikeViewModel(private val repository: BikeDefaultRepository) : ViewModel() {

    private val _bikes = MutableStateFlow<List<Bike>>(emptyList())
    val bikes: StateFlow<List<Bike>> = _bikes

    init {
        viewModelScope.launch {
            repository.getAllBikes().collect {
                _bikes.value = it
            }
        }
    }

    // Use a suspend function to add a new MyModel object to the database
    suspend fun addBike(bike: Bike) {
        repository.insertBike(bike)
    }
}