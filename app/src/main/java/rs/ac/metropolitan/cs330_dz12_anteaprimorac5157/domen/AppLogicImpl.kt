package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domen

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.repository.DBRepository
import java.time.LocalDate
import javax.inject.Inject

class AppLogicImpl @Inject constructor(
    private val dbRepository: DBRepository
) : AppLogic{
    override suspend fun getIntakes(): Flow<List<WaterIntake>> {
        return dbRepository.getIntakes()
    }

    override suspend fun insertIntake(intake: WaterIntake): Long {
        return dbRepository.insertIntake(intake)
    }

    override suspend fun intakeByDate(date: LocalDate): Flow<List<WaterIntake>> {
        return dbRepository.getIntakes().filter { intakes ->
            intakes.any { it.date.toLocalDate() == date}
        }
    }

    override suspend fun deleteIntake(intake: WaterIntake) {
        dbRepository.deleteIntake(intake)
    }
}