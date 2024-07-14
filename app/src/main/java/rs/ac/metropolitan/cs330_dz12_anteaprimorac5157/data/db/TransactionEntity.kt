package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain.Category
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain.Currency
import java.time.LocalDateTime

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val amount: Double,
    val currency: Currency,
    val category: Category,
    val date: LocalDateTime,
    val note: String?
)