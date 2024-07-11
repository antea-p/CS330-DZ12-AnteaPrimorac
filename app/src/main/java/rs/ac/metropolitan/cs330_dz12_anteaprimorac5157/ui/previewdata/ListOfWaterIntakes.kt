package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.previewdata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domen.IntakeAmount
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domen.WaterIntake
import java.time.LocalDateTime

var waterIntakes: LiveData<List<WaterIntake>> = MutableLiveData(
    listOf(
        WaterIntake(1, LocalDateTime.of(2024, 1, 3, 10, 15), IntakeAmount.D25, "I was thirsty!"),
        WaterIntake(2, LocalDateTime.of(2024, 1, 3, 15, 45), IntakeAmount.D25, "I drink water because alarm remind me!"),
        WaterIntake(3, LocalDateTime.of(2024, 1, 3, 18, 0), IntakeAmount.D50, "I drink water to avoid alcohol!"),
        WaterIntake(4, LocalDateTime.of(2024, 1, 4, 10, 30), IntakeAmount.D25, "I drink water because I was thirsty!"),
        WaterIntake(5, LocalDateTime.of(2024, 1, 4, 15, 0), IntakeAmount.D25, "I drink water because I was thirsty!"),
        WaterIntake(6, LocalDateTime.of(2024, 1, 4, 18, 0), IntakeAmount.D100, "I drink water to avoid alcohol!"),
        WaterIntake(7, LocalDateTime.of(2024, 1, 5, 10, 0), IntakeAmount.D75, "I drink water because I was thirsty!"),
        WaterIntake(8, LocalDateTime.of(2024, 1, 5, 15, 0), IntakeAmount.D25, "I drink water because I was thirsty!"),
        WaterIntake(9, LocalDateTime.of(2024, 1, 5, 18, 0), IntakeAmount.D50, "I drink water to avoid alcohol!"),

    )
)