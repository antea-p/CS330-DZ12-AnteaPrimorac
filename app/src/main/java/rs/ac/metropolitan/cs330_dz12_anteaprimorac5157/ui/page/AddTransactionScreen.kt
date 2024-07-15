package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.page

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain.Category
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain.Currency
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain.Transaction
import java.time.LocalDateTime

@Composable
fun AddTransactionScreen(
    transaction: Transaction? = null,
    goBack: () -> Unit,
    save: (Transaction) -> Unit
) {
    var amount by remember { mutableDoubleStateOf(transaction?.amount ?: 0.0) }
    var currency by remember { mutableStateOf(transaction?.currency ?: Currency.USD) }
    var category by remember { mutableStateOf(transaction?.category ?: Category.OTHER) }
    var date by remember { mutableStateOf(transaction?.date ?: LocalDateTime.now()) }
    var note by remember { mutableStateOf(transaction?.note ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderSection(goBack, transaction != null)
        AmountInput(amount) { amount = it }
        CurrencySelection(currency) { currency = it }
        CategorySelection(category) { category = it }
        DateTimeSelection(date) { date = it }
        NoteInput(note) { note = it }
        Spacer(modifier = Modifier.weight(1f))
        AddButton(transaction != null) {
            save(Transaction(transaction?.id, amount, currency, category, date, note))
            goBack()
        }
    }
}

@Composable
fun HeaderSection(goBack: () -> Unit, isEditing: Boolean) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                modifier = Modifier
                    .background(Color.Transparent)
                    .scale(1.5f),
                onClick = goBack
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Text(
                text = if (isEditing) "Edit Transaction" else "Add Transaction",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Composable
fun AmountInput(amount: Double, onAmountChange: (Double) -> Unit) {
    OutlinedTextField(
        value = amount.toString(),
        onValueChange = {
            val newAmount = it.toDoubleOrNull() ?: amount
            onAmountChange(newAmount)
        },
        label = { Text("Amount") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun CurrencySelection(selectedCurrency: Currency, onCurrencySelect: (Currency) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Currency.values().forEach { currencyOption ->
            Button(
                onClick = { onCurrencySelect(currencyOption) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedCurrency == currencyOption)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(currencyOption.name)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategorySelection(selectedCategory: Category, onCategorySelect: (Category) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedCategory.name,
            onValueChange = { },
            label = { Text("Category") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            Category.values().forEach { categoryOption ->
                DropdownMenuItem(
                    text = { Text(categoryOption.name) },
                    onClick = {
                        onCategorySelect(categoryOption)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun DateTimeSelection(selectedDate: LocalDateTime, onDateTimeSelect: (LocalDateTime) -> Unit) {
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        DatePicker(selectedDate) { newDate ->
            onDateTimeSelect(newDate)
        }
        Spacer(modifier = Modifier.width(16.dp))
        TimePicker(selectedDate) { newTime ->
            onDateTimeSelect(newTime)
        }
    }
}

@Composable
fun NoteInput(note: String, onNoteChange: (String) -> Unit) {
    OutlinedTextField(
        value = note,
        onValueChange = onNoteChange,
        label = { Text("Note") },
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
fun AddButton(isEditing: Boolean, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(if (isEditing) "Update" else "Add")
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
    AddTransactionScreen(null, {}, {})
}