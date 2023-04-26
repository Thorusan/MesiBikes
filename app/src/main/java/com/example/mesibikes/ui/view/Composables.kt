package com.example.mesibikes.ui.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mesibikes.model.bikesList
import com.example.mesibikes.ui.theme.MesiBikesTheme
import java.time.LocalDateTime
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldErrorValidation(
    text: String,
    onTextChanged: (String) -> Unit,
    error: String?,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        TextField(
            value = text,
            onValueChange = onTextChanged,
            label = { Text(label) },
            isError = error != null,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        if (error != null) {
            Text(
                text = error,
                color = Color.Red,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}

@Composable
fun DateTimePicker(
    label: String,
    context: Context,
    selectedDateStart: String,
    selectedTimeStart: String,
    onDateTimeSelected: (date: LocalDateTime) -> Unit,
    modifier: Modifier = Modifier
) {
    val calendar = Calendar.getInstance()
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]

    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH]
    val day = calendar[Calendar.DAY_OF_MONTH]

    var selectedDateFrom by remember { mutableStateOf(selectedDateStart) }
    var selectedTimeFrom by remember { mutableStateOf(selectedTimeStart) }

    var selectedYear by remember { mutableStateOf( year) }
    var selectedMonth by remember { mutableStateOf(month) }
    var selectedDay by remember { mutableStateOf(day) }

    val timePicker = TimePickerDialog(
        context,
        { _, selectedHour: Int, selectedMinute: Int ->
            selectedTimeFrom = "$selectedHour:$selectedMinute"
            onDateTimeSelected(
                LocalDateTime.of(
                    selectedYear,
                    selectedMonth,
                    selectedDay,
                    hour,
                    minute
                )
            )
        }, hour, minute, true
    )

    val datePicker = DatePickerDialog(
        context,
        { _: DatePicker, selectedYearDialog: Int, selectedMonthDialog: Int, selectedDayDialog: Int ->
            selectedDateFrom = "${day}.${month+1}.$year"
            selectedYear = selectedYearDialog
            selectedMonth = selectedMonthDialog + 1
            selectedDay = selectedDayDialog

            timePicker.show()
        },
        year,
        month,
        day
    )

    Box(modifier = modifier.fillMaxWidth()) {
        Text(text = label, modifier = Modifier.align(Alignment.CenterStart))

        Button(
            onClick = {
                datePicker.show()
            },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Text(text = if (selectedDateFrom.isEmpty()) {
                "Izberi datum / čas"
            } else {
                "$selectedDateFrom $selectedTimeFrom"
            })
        }
    }
}

@Composable
fun Slider(
    onValueChange: (sliderValue : Float) -> Unit,
    modifier: Modifier = Modifier
) {
    var sliderPosition by remember { mutableStateOf(0f) }
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Slider(
            value = sliderPosition,
            onValueChange = {
                sliderPosition = it
                onValueChange(sliderPosition)
            },
            valueRange = 0f..100f,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview(device = Devices.PIXEL_4, showBackground = true, showSystemUi = true)
@Composable
fun SliderPreview() {
    MesiBikesTheme {
        Slider({})
    }
}