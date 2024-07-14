package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.previewdata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain.Category
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain.Currency
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain.Transaction
import java.time.LocalDateTime

var previewTransactions: LiveData<List<Transaction>> =  MutableLiveData(
        listOf(
            Transaction(1, 50.0, Currency.USD, Category.FOOD, LocalDateTime.now().minusDays(1), "Grocery shopping"),
            Transaction(2, 20.0, Currency.EUR, Category.ENTERTAINMENT, LocalDateTime.now(), "Movie ticket"),
            Transaction(3, 30.0, Currency.USD, Category.GASOLINE, LocalDateTime.now(), "Fuel")
        )
)


