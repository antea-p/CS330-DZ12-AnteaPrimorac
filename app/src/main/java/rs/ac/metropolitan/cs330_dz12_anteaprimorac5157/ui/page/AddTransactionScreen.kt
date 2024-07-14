package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.page

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain.Category
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain.Currency
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain.Transaction
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun AddTransactionScreen(
    goBack: () -> Unit,
    save: (Transaction) -> Unit
) {
    var amount by remember { mutableStateOf(0.0) }
    var currency by remember { mutableStateOf(Currency.USD) }
    var category by remember { mutableStateOf(Category.OTHER) }
    var date by remember { mutableStateOf(LocalDateTime.now()) }
    var note by remember { mutableStateOf("") }

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
                    text = "Add Transaction",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf(10.0, 20.0, 50.0, 100.0).forEach { preset ->
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clickable { amount = preset }
                        .border(
                            width = 2.dp,
                            color = if (amount == preset) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                            shape = MaterialTheme.shapes.medium
                        )
                ) {
                    Text(
                        text = "$${preset.toInt()}",
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }

        Text(text = "Selected amount: $${amount}")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Currency.entries.forEach { currencyOption ->
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clickable { currency = currencyOption }
                        .border(
                            width = 2.dp,
                            color = if (currency == currencyOption) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                            shape = MaterialTheme.shapes.medium
                        )
                ) {
                    Text(
                        text = currencyOption.name,
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Category.values().forEach { categoryOption ->
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clickable { category = categoryOption }
                        .border(
                            width = 2.dp,
                            color = if (category == categoryOption) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                            shape = MaterialTheme.shapes.medium
                        )
                ) {
                    Text(
                        text = categoryOption.name.take(1),
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }

        Text(text = "Selected date: ${date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))}")
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            DatePicker(date) { newDate -> date = newDate }
            Spacer(modifier = Modifier.width(16.dp))
            TimePicker(date) { newTime -> date = newTime }
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = note,
            onValueChange = { note = it },
            label = { Text("Note") },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = {
            save(Transaction(null, amount, currency, category, date, note))
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
fun AddTransactionScreenPreview() {
    AddTransactionScreen({}, {})
}