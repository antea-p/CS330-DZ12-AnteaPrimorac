package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domen

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface AppLogic {
    suspend fun getIntakes(): Flow<List<WaterIntake>>
    suspend fun insertIntake(intake: WaterIntake): Long
    suspend fun intakeByDate(date: LocalDate): Flow<List<WaterIntake>>
    suspend fun deleteIntake(intake: WaterIntake)
}