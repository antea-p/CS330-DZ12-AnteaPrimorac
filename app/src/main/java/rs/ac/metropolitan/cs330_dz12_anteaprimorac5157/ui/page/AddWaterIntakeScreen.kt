package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.page

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domen.IntakeAmount
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domen.WaterIntake
import java.time.LocalDateTime

@Composable
fun AddWaterIntakeScreen(
    goBack: () -> Unit,
    save: (WaterIntake) -> Unit
) {
    var amount by remember { mutableStateOf(IntakeAmount.D25) } // Default to D25
    var note by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(LocalDateTime.now()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ){
                IconButton(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .scale(1.5f),
                    onClick = { goBack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Text(
                    text = "Add Water Intake",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IntakeAmount.entries.forEach { intakeAmount ->
                Image(
                    painter = painterResource(WaterIntake(null, LocalDateTime.now(), intakeAmount, null).imageForAmount()),
                    contentDescription = "Water intake amount",
                    modifier = Modifier
                        .height(100.dp)
                        .clickable { amount = intakeAmount }
                )
            }
        }
        Text(text = "Selected amount: $amount ml")
        Text(text = "Selected date: ${date.dayOfMonth}.${date.monthValue}.${date.year} ${date.hour}:${date.minute}")
        Spacer(modifier = Modifier.height(16.dp))
        Row (horizontalArrangement = Arrangement.SpaceBetween){
            DatePicker(date) { newDate -> date = newDate }
            Spacer(modifier = Modifier.padding(16.dp))
            TimePicker(date) { newTime -> date = date.withHour(newTime.hour).withMinute(newTime.minute) }
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = note,
            onValueChange = { note = it },
            label = { Text("Note") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = {
            save(WaterIntake(null, date, amount, note))
            goBack()
        }) {
            Text("Add")
        }
    }
}

@Composable
fun DatePicker(currentDate: LocalDateTime, onDateChange: (LocalDateTime) -> Unit) {
    val context = LocalContext.current
    Button(onClick = {
        val dialog = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                onDateChange(LocalDateTime.of(year, month + 1, dayOfMonth, currentDate.hour, currentDate.minute))
            },
            currentDate.year,
            currentDate.monthValue - 1,
            currentDate.dayOfMonth
        )
        dialog.show()
    }) {
        Text(text = "Select Date")
    }
}

@Composable
fun TimePicker(currentTime: LocalDateTime, onTimeChange: (LocalDateTime) -> Unit) {
    val context = LocalContext.current
    Button(onClick = {
        val dialog = TimePickerDialog(
            context,
            { _, hourOfDay, minute ->
                onTimeChange(currentTime.withHour(hourOfDay).withMinute(minute))
            },
            currentTime.hour,
            currentTime.minute,
            true
        )
        dialog.show()
    }) {
        Text(text = "Select Time")
    }
}

@Preview
@Composable
fun AddWaterIntakePreview() {
    AddWaterIntakeScreen({}, {})
}