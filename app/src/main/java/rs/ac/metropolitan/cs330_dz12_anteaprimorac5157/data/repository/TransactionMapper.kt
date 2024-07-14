package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.repository

import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.db.TransactionEntity
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain.Transaction

class TransactionMapper {
    fun toEntity(transaction: Transaction): TransactionEntity {
        return TransactionEntity(
            id = transaction.id,
            amount = transaction.amount,
            currency = transaction.currency,
            category = transaction.category,
            date = transaction.date,
            note = transaction.note
        )
    }

    fun fromEntity(entity: TransactionEntity): Transaction {
        return Transaction(
            id = entity.id,
            amount = entity.amount,
            currency = entity.currency,
            category = entity.category,
            date = entity.date,
            note = entity.note
        )
    }
}