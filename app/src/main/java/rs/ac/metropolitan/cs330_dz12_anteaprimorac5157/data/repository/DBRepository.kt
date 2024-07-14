package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.repository

import kotlinx.coroutines.flow.Flow
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain.Transaction

interface DBRepository {
    suspend fun getTransactions(): Flow<List<Transaction>>
    suspend fun insertTransaction(transaction: Transaction): Long
    suspend fun deleteTransaction(transaction: Transaction)
}