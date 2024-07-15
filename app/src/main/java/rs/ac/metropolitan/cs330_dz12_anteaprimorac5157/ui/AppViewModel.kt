package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain.AppLogic
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain.Transaction
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val appLogic: AppLogic
) : ViewModel() {
    private val _transactions = MutableLiveData<List<Transaction>>()
    val transactions: LiveData<List<Transaction>>
        get() = _transactions

    init {
        loadTransactions()
    }

    fun loadTransactions() {
        viewModelScope.launch {
            Log.d("AppViewModel", "Starting to load transactions")
            appLogic.getTransactions().collect { transactions ->
                Log.d("AppViewModel", "Received ${transactions.size} transactions")
                _transactions.value = transactions
                Log.d("AppViewModel", "Updated _transactions value")
            }
        }
    }

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            Log.d("AppViewModel", "Adding new transaction")
            appLogic.insertTransaction(transaction)
            Log.d("AppViewModel", "Transaction added, reloading transactions")
            loadTransactions()
        }
    }

    fun updateTransaction(transaction: Transaction) {
        viewModelScope.launch {
            appLogic.updateTransaction(transaction)
            loadTransactions()
        }
    }

    fun deleteTransaction(transaction: Transaction) {
        viewModelScope.launch {
            appLogic.deleteTransaction(transaction)
        }
    }

    fun getTransactionsByDate(date: LocalDate) {
        viewModelScope.launch {
            appLogic.getTransactionsByDate(date)
        }
    }
}