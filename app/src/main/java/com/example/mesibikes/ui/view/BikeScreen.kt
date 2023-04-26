package com.example.mesibikes.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mesibikes.R
import com.example.mesibikes.db.Bike
import com.example.mesibikes.db.User
import com.example.mesibikes.ui.theme.MesiBikesTheme
import java.time.LocalDateTime

@Composable
fun BikeScreen(
    bikes: List<Bike>,
    onAddBike: (bike: Bike, user: User) -> Unit,
) {
    val isMainPage = remember { mutableStateOf(true) }
    val chosenBike = remember { mutableStateOf<Bike?>(null) }

    if (isMainPage.value) {
        MainPage(bikes = bikes) {
            isMainPage.value = false
            chosenBike.value = it
        }
    } else {
        DetailPage(chosenBike.value!!, onAddReservation = { user ->
            isMainPage.value = true
            onAddBike(chosenBike.value!!, user)
        })
    }
}

@Composable
fun MainPage(
    bikes: List<Bike>,
    onAddReservation: (bike: Bike) -> Unit
) {
    Box(modifier = Modifier.fillMaxHeight()) {
        LazyColumn(
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 150.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(bikes) { bike ->
                ItemBike(bike) {
                    onAddReservation(bike)
                }
                Divider(modifier = Modifier.padding(top = 8.dp))
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPage(
    bike: Bike,
    onAddReservation: (user: User) -> Unit
) {
    val mandatoryFieldText = stringResource(R.string.field_mandatory)
    var isFormValid by remember { mutableStateOf(false) }
    var textBorrower by remember { mutableStateOf("") }
    var errorBorrower by remember { mutableStateOf<String?>(null) }
    var textDepartment by remember { mutableStateOf("") }
    var errorDepartment by remember { mutableStateOf<String?>(null) }
    var textPurpose by remember { mutableStateOf("") }
    var errorPurpose by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current
    var selectedDateTimeFrom by remember { mutableStateOf(LocalDateTime.now()) }
    var selectedDateTimeTo by remember { mutableStateOf(LocalDateTime.now()) }

    var distance by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            TextFieldErrorValidation(
                text = textBorrower,
                onTextChanged = { newText ->
                    textBorrower = newText
                    errorBorrower = null // Clear any previous error
                },
                error = errorBorrower,
                label = "Izposojevalec",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(vertical = 16.dp))

            TextFieldErrorValidation(
                text = textDepartment,
                onTextChanged = { newText ->
                    textDepartment = newText
                    errorDepartment = null // Clear any previous error
                },
                error = errorDepartment,
                label = "Sektor",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(vertical = 16.dp))

            DateTimePicker(
                label = "Rezervacija OD: ",
                context = context,
                selectedDateStart = "",
                selectedTimeStart = "",
                onDateTimeSelected = {
                    selectedDateTimeFrom = it
                },
            )

            Spacer(modifier = Modifier.padding(vertical = 16.dp))

            DateTimePicker(
                label = "Rezervacija DO: ",
                context = context,
                selectedDateStart = "",
                selectedTimeStart = "",
                onDateTimeSelected = {
                    selectedDateTimeTo = it
                }
            )

            Spacer(modifier = Modifier.padding(vertical = 16.dp))

            Slider(onValueChange = {
                distance = it
            })

            TextField(
                value = "${String.format("%.2f", distance)} km",
                onValueChange = { },
                enabled = false,
                label = { Text("Razdalja") }
            )

            Spacer(modifier = Modifier.padding(vertical = 16.dp))

            TextFieldErrorValidation(
                text = textPurpose,
                onTextChanged = { newText ->
                    textPurpose = newText
                    errorPurpose = null // Clear any previous error
                },
                error = errorPurpose,
                label = "Namen",
                modifier = Modifier.fillMaxWidth()
            )
        }

        Button(
            onClick = {
                if (textBorrower.isBlank()) {
                    errorBorrower = mandatoryFieldText
                    isFormValid = false
                } else {
                    isFormValid = true
                }

                if (textDepartment.isBlank()) {
                    errorDepartment = mandatoryFieldText
                    isFormValid = false
                } else {
                    isFormValid = true
                }

                if (textPurpose.isBlank()) {
                    errorPurpose = mandatoryFieldText
                    isFormValid = false
                } else {
                    isFormValid = true
                }

                if (isFormValid) {
                    val borrowerSplittedText = textBorrower.split(" ")

                    val user = User(
                        name = borrowerSplittedText[0].trim(),
                        surname = borrowerSplittedText.subList(1, borrowerSplittedText.size)
                            .toString(),
                        department = textDepartment,
                        reservationStart = selectedDateTimeFrom,
                        reservationEnd = selectedDateTimeTo,
                        distance = distance.toDouble(),
                        borrowPurpose = textPurpose
                    )

                    onAddReservation(user)
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(58.dp)
                .fillMaxWidth()
        ) {
            Text(text = "DODAJ", fontSize = 20.sp)
        }
    }
}

@Composable
fun ItemBike(
    bike: Bike,
    onAddReservation: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .padding(start = 8.dp, top = 16.dp),

            text = bike.name
        )

        Text(
            modifier = Modifier
                .padding(start = 8.dp, top = 16.dp),

            text = bike.status.description
        )

        Button(
            onClick = { onAddReservation() },
            modifier = Modifier
                .padding(12.dp)

                .height(40.dp)
        ) {
            Text(text = "DODAJ", fontSize = 20.sp)
        }
    }
}


@Preview(device = Devices.PIXEL_4, showBackground = true, showSystemUi = true)
@Composable
fun OrderScreenPreview() {
    MesiBikesTheme {
        /*BikeScreen(
            bikesList,
        ) {}*/
    }
}
