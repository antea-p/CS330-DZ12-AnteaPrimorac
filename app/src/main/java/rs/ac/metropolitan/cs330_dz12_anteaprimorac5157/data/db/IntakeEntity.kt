package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domen.IntakeAmount
import java.time.LocalDateTime

@Entity(tableName = "intakes")
data class IntakeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val date: LocalDateTime,
    val amount: IntakeAmount,
    var note: String?
)