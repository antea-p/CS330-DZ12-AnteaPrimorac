package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui

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
            appLogic.getTransactions().collect { transactions ->
                _transactions.value = transactions
            }
        }
    }

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            appLogic.insertTransaction(transaction)
            loadTransactions() // Ponovno ucitavanje transakcija nakon dodavanja nove
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