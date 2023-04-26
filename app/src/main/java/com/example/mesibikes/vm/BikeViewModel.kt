package com.example.mesibikes.vm

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
import java.time.LocalDateTime

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
                        bikes.forEach { bike ->
                            val today = LocalDateTime.now()

                            val isBikeNotAvailable = bike.user != null
                                    && today.isAfter(bike.user?.reservationStart)
                                    && today.isBefore(bike.user?.reservationEnd)

                            if (isBikeNotAvailable) {
                                bike.status = BikeStatus.NOT_AVAILABLE
                            } else {
                                bike.status = BikeStatus.AVAILABLE
                            }
                        }

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
        bike.user = user
        bike.lastReservation = LocalDateTime.now()
        bike.nextReservation = user.reservationStart
        bike.distance = bike.distance + user.distance

        repository.updateBike(bike)
        repository.incrementReservations(bike.name)
    }
}