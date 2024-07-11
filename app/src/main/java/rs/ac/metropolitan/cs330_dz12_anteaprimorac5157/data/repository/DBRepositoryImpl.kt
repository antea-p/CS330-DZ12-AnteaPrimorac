package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.db.IntakeDao
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domen.WaterIntake
import javax.inject.Inject

class DBRepositoryImpl @Inject constructor(
    private val intakeDao: IntakeDao,
    private val waterIntakeMapper: WaterIntakeMapper
) : DBRepository{
    override suspend fun getIntakes(): Flow<List<WaterIntake>>{
        return intakeDao.getIntakes().map { intakeEntities ->
            intakeEntities.map { intakeEntity ->
                waterIntakeMapper.fromEntity(intakeEntity)
            }
        }
    }

    override suspend fun insertIntake(intake: WaterIntake): Long{
        return intakeDao.insertIntake(waterIntakeMapper.toEntity(intake))
    }

    override suspend fun deleteIntake(intake: WaterIntake){
        intakeDao.deleteIntake(waterIntakeMapper.toEntity(intake))
    }
}