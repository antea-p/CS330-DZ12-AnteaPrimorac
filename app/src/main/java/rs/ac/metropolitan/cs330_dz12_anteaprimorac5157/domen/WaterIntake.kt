package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domen

import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.R
import java.time.LocalDateTime

data class WaterIntake(
    val id: Int?,
    val date: LocalDateTime,
    val amount: IntakeAmount,
    val note: String?
){
    fun imageForAmount(): Int {
        return when(amount){
            IntakeAmount.D25 -> R.drawable.bottle_25
            IntakeAmount.D50 -> R.drawable.bottle_50
            IntakeAmount.D75 -> R.drawable.bottle_75
            IntakeAmount.D100 -> R.drawable.bottle_100
        }
    }

}

enum class IntakeAmount{
    D25, D50, D75, D100
}