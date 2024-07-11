package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface IntakeDao {

    @Upsert
    suspend fun insertIntake(intake: IntakeEntity): Long

    @Delete
    suspend fun deleteIntake(intake: IntakeEntity)

    @Query("SELECT * FROM intakes")
    fun getIntakes(): Flow<List<IntakeEntity>>
}