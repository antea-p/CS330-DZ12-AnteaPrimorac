package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.page

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.R
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
    navigateToAddTransaction: () -> Unit,
    navigateToTransactionDetails: (Int) -> Unit
) {
    val transactionsList by transactions.observeAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        loadTransactions()
    }

    LaunchedEffect(transactionsList) {
        Log.d("TransactionListScreen", "Received ${transactionsList.size} transactions")
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
        TransactionList(
            transactions = transactionsList,
            paddingValues = innerPadding,
            navigateToTransactionDetails = navigateToTransactionDetails
        )
    }
}

@Composable
fun TransactionList(
    transactions: List<Transaction>,
    paddingValues: PaddingValues,
    navigateToTransactionDetails: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
    ) {
        if (transactions.isNotEmpty()) {
            val groupedTransactions = transactions.groupBy { it.date.toLocalDate() }
            groupedTransactions.forEach { (date, transactionsForDate) ->
                item {
                    Text(
                        text = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
                items(transactionsForDate) { transaction ->
                    TransactionRowView(
                        transaction = transaction,
                        onTransactionClick = navigateToTransactionDetails
                    )
                }
            }
        }
    }
}

@Composable
fun TransactionRowView(
    transaction: Transaction,
    onTransactionClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onTransactionClick(transaction.id ?: -1) },
        shape = MaterialTheme.shapes.medium,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = transaction.amount.toString(),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
            Icon(
                painter = painterResource(id = if (transaction.currency == Currency.USD) R.drawable.ic_attach_money else R.drawable.ic_euro),
                contentDescription = "Currency",
                modifier = Modifier.size(size = if (transaction.currency == Currency.USD) 20.dp else 17.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = transaction.category.name,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "Time: ${transaction.date.format(DateTimeFormatter.ofPattern("HH:mm, dd/MM/yyyy"))}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "View Details",
                modifier = Modifier.size(24.dp)
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
            navigateToAddTransaction = {},
            navigateToTransactionDetails = {}
        )
}
