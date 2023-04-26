package com.example.mesibikes.vm

import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mesibikes.db.Bike
import com.example.mesibikes.db.BikeStatus
import com.example.mesibikes.db.User
import com.example.mesibikes.model.BikeDefaultRepository
import com.example.mesibikes.model.bikesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import javax.net.ssl.SSLEngineResult.Status

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
            withContext(Dispatchers.IO) {
                repository.getAllBikes().collect { bikes ->
                    if (bikes.isEmpty()) {
                        bikesList.forEach {
                            addBike(it)
                        }
                    } else {
                        _bikes.value = bikes
                    }
                }
            }
        }
    }

    private suspend fun addBike(bike: Bike) {
        repository.insertBike(bike)
    }

    suspend fun addReservation(bike: Bike, user: User) {
        bike.status = BikeStatus.NOT_AVAILABLE
        bike.user = user
        bike.lastReservation = user.reservationEnd
        bike.nextReservation = user.reservationStart
        bike.distance  = bike.distance + user.distance

        repository.updateBike(bike)
        repository.incrementReservations(bike.name)
    }
}