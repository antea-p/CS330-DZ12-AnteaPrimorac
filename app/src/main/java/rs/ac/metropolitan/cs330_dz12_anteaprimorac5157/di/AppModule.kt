package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.db.IntakeDao
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.db.WaterIntakeDatabase
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.repository.DBRepository
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.repository.DBRepositoryImpl
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.repository.WaterIntakeMapper
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domen.AppLogic
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domen.AppLogicImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext

    }

    @Provides
    @Singleton
    fun provideIntakeDao(database: WaterIntakeDatabase): IntakeDao {
        return database.intakeDao
    }

    @Provides
    @Singleton
    fun provideWaterIntakeDatabase(app: Application): WaterIntakeDatabase {
        return Room.databaseBuilder(
            app,
            WaterIntakeDatabase::class.java,
            "water_intake_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDBRepository(intakeDao: IntakeDao, mapper: WaterIntakeMapper): DBRepository {
        return DBRepositoryImpl(intakeDao, mapper)
    }

    @Provides
    fun provideWaterIntakeMapper(): WaterIntakeMapper {
        return WaterIntakeMapper()
    }

    @Provides
    fun provideAppLogic(dbRepository: DBRepository): AppLogic {
        return AppLogicImpl(dbRepository)
    }

}