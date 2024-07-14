package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.repository

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
        return transactionDao.getTransactions().map { transactionEntities ->
            transactionEntities.map { transactionEntity ->
                transactionMapper.fromEntity(transactionEntity) }
        }
    }

    override suspend fun insertTransaction(transaction: Transaction): Long {
        return transactionDao.insertTransaction(transactionMapper.toEntity(transaction))
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        transactionDao.deleteTransaction(transactionMapper.toEntity(transaction))
    }
}