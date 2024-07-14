package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface AppLogic {
    suspend fun getTransactions(): Flow<List<Transaction>>
    suspend fun insertTransaction(transaction: Transaction): Long
    suspend fun deleteTransaction(transaction: Transaction)
    // TODO ?
//    suspend fun getTransactionById(id: Int): Transaction?
    suspend fun getTransactionsByDate(date: LocalDate): Flow<List<Transaction>>
}