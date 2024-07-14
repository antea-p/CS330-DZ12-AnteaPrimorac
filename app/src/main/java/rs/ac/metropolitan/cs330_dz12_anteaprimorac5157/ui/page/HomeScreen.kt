package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.R
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.theme.CS330DZ12AnteaPrimorac5157

@Composable
fun HomeScreen(
    addTransaction: () -> Unit,
    openTransactionList: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            // TODO: zamjena slike
            painter = painterResource(R.drawable.water_bottle),
            contentDescription = "Transaction icon",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clickable { openTransactionList() },
        )
        Button(
            onClick = { addTransaction() },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "Add Transaction",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    CS330DZ12AnteaPrimorac5157 {
        HomeScreen({}, {})
    }
}