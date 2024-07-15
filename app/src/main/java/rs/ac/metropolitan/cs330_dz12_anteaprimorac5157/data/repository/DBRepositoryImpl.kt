package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.db.TransactionDao
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain.Transaction
import javax.inject.Inject

class DBRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao,
    private val transactionMapper: TransactionMapper
) : DBRepository {

    override suspend fun getTransactions(): Flow<List<Transaction>> {
        Log.d("DBRepositoryImpl", "Fetching transactions from DAO")
        return transactionDao.getTransactions().map { transactionEntities ->
            Log.d("DBRepositoryImpl", "Mapping ${transactionEntities.size} entities to transactions")
            transactionEntities.map { transactionEntity ->
                transactionMapper.fromEntity(transactionEntity)
            }
        }
    }

    override suspend fun insertTransaction(transaction: Transaction): Long {
        val entity = transactionMapper.toEntity(transaction)
        val newId = transactionDao.insertTransaction(entity)
        Log.d("DBRepositoryImpl", "Inserted transaction with ID: $newId")
        return newId
    }

    override suspend fun updateTransaction(transaction: Transaction) {
        transactionDao.updateTransaction(transactionMapper.toEntity(transaction))
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        transactionDao.deleteTransaction(transactionMapper.toEntity(transaction))
    }
}