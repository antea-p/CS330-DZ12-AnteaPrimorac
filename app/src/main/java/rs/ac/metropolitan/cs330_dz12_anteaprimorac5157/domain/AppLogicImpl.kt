package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.repository.DBRepository
import java.time.LocalDate
import javax.inject.Inject

class AppLogicImpl @Inject constructor(
    private val dbRepository: DBRepository
) : AppLogic {
    override suspend fun getTransactions(): Flow<List<Transaction>> {
        return dbRepository.getTransactions()
    }

    override suspend fun insertTransaction(transaction: Transaction): Long {
        return dbRepository.insertTransaction(transaction)
    }

    // TODO: check

    override suspend fun getTransactionsByDate(date: LocalDate): Flow<List<Transaction>> {
        return dbRepository.getTransactions().map { transactions ->
            transactions.filter {
                it.date.toLocalDate() == date
            }
        }
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        dbRepository.deleteTransaction(transaction)
    }

}