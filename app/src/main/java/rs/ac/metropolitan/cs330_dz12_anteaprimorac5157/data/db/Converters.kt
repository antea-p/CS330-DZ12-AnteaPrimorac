package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.db

import androidx.room.TypeConverter
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain.Category
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain.Currency
import java.time.LocalDateTime

class Converters {
    @TypeConverter
    fun fromTimestamp(value: String?): LocalDateTime? {
        return value?.let { LocalDateTime.parse(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): String? {
        return date?.toString()
    }

    @TypeConverter
    fun fromCurrency(value: Currency) = value.name

    @TypeConverter
    fun toCurrency(value: String) = enumValueOf<Currency>(value)

    @TypeConverter
    fun fromCategory(value: Category) = value.name

    @TypeConverter
    fun toCategory(value: String) = enumValueOf<Category>(value)
}