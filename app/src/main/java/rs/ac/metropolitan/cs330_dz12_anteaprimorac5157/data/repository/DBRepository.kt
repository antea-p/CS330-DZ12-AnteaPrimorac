package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.repository

import kotlinx.coroutines.flow.Flow
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domen.WaterIntake

interface DBRepository {
    suspend fun getIntakes(): Flow<List<WaterIntake>>
    suspend fun insertIntake(intake: WaterIntake): Long
    suspend fun deleteIntake(intake: WaterIntake)
}