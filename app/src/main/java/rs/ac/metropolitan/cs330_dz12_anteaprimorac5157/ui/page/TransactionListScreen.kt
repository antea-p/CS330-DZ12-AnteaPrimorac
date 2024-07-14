package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain.Category
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain.Currency
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain.Transaction
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionListScreen(
    transactions: LiveData<List<Transaction>>,
    padding: PaddingValues = PaddingValues(16.dp),
    loadTransactions: () -> Unit,
    navigateToAddTransaction: () -> Unit
) {
    LaunchedEffect(Unit) {
        loadTransactions()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Transactions",
                    style = MaterialTheme.typography.headlineLarge
                )
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navigateToAddTransaction() }) {
                Icon(Icons.Filled.Add, contentDescription = "Add transaction")
            }
        },
        modifier = Modifier.padding(padding)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(padding)
        ) {
            if (!transactions.value.isNullOrEmpty()) {
                val groupedTransactions = transactions.value!!.groupBy { it.date.toLocalDate() }
                groupedTransactions.forEach { (date, transactionsForDate) ->
                    item {
                        Text(
                            text = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    items(transactionsForDate) { transaction ->
                        TransactionRowView(transaction)
                    }
                }
            }
        }
    }
}

@Composable
fun TransactionRowView(
    transaction: Transaction,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = MaterialTheme.shapes.medium,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(16.dp)
        ) {
            Column {
                Text(
                    text = transaction.category.name,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = transaction.date.format(DateTimeFormatter.ofPattern("HH:mm")),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Text(
                text = "${transaction.amount} ${transaction.currency}",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview
@Composable
fun TransactionListScreenPreview() {
    val mockTransactions = MutableLiveData(
        listOf(
            Transaction(1, 50.0, Currency.USD, Category.FOOD, LocalDateTime.now().minusDays(1), "Grocery shopping"),
            Transaction(2, 20.0, Currency.EUR, Category.ENTERTAINMENT, LocalDateTime.now(), "Movie ticket"),
            Transaction(3, 30.0, Currency.USD, Category.GASOLINE, LocalDateTime.now(), "Fuel")
        )
    )

    TransactionListScreen(
            transactions = mockTransactions,
            loadTransactions = {},
            navigateToAddTransaction = {}
        )
}
