package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain

import java.time.LocalDateTime

data class Transaction(
    val id: Int?,
    val amount: Double,
    val currency: Currency,
    val category: Category,
    val date: LocalDateTime,
    val note: String?
)

enum class Currency {
    USD, EUR
}

enum class Category {
    FOOD, INCOME, GASOLINE, ENTERTAINMENT, BILL, OTHER
}