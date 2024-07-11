package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domen.WaterIntake
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.previewdata.waterIntakes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaterIntakeListScreen(
    intakes: LiveData<List<WaterIntake>>,
    padding: PaddingValues = PaddingValues(16.dp),
    loadWaterIntake: () -> Unit,
    navigateToAddIntake: () -> Unit
) {
    LaunchedEffect(Unit) {
        loadWaterIntake()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Water intakes",
                    style = MaterialTheme.typography.headlineLarge
                )
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navigateToAddIntake() }) {
                Icon(Icons.Filled.Add, contentDescription = "Add intake")
            }
        },
        modifier = androidx.compose.ui.Modifier.padding(padding)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(padding)
        ) {
            if (!intakes.value.isNullOrEmpty()) {
                val groupedIntakes = intakes.value!!.groupBy { it.date.toLocalDate() }
                groupedIntakes.forEach { (date, intakesForDate) ->
                    item {
                        Text(
                            text = date.dayOfMonth.toString() + "." + date.monthValue + "." + date.year.toString() + ".",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    items(intakesForDate) { intake ->  // Use intakesForDate instead of intakes.value!!
                        WaterIntakeRowView(intake)
                    }
                }
            }
        }
    }
}

@Composable
fun WaterIntakeRowView(
    intake: WaterIntake,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = MaterialTheme.shapes.medium,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Image(
                painter = painterResource(intake.imageForAmount()),
                contentDescription = "Water bottle",
                modifier = Modifier
                    .height(32.dp)
                    .padding(start = 16.dp)
            )
            Column {
                Text(
                    text = intake.date.dayOfMonth.toString() + "." +
                            intake.date.monthValue + "." +
                            intake.date.year + " " +
                            intake.date.hour + ":" + intake.date.minute,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(start = 16.dp)
                )
                Text(
                    text = intake.note.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun WaterIntakeListScreenPreview() {
    WaterIntakeListScreen(
        intakes = waterIntakes,
        loadWaterIntake = {},
        navigateToAddIntake = {}
    )
}