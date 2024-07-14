package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.db.TransactionDao
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.db.TransactionDatabase
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.repository.DBRepository
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.repository.DBRepositoryImpl
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.repository.TransactionMapper
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.di.AppModule
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain.AppLogic
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain.AppLogicImpl
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
object TestAppModule {

//    @Provides
//    @Singleton
//    fun provideApplication(): Application {
//        return HiltTestApplication()
//    }

    @Provides
    fun provideTransactionDatabase(app: Application): TransactionDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            TransactionDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideTransactionDao(database: TransactionDatabase): TransactionDao {
        return database.transactionDao
    }

    @Provides
    @Singleton
    fun provideDBRepository(transactionDao: TransactionDao, mapper: TransactionMapper): DBRepository {
        return DBRepositoryImpl(transactionDao, mapper)
    }

    @Provides
    fun provideWaterIntakeMapper(): TransactionMapper {
        return TransactionMapper()
    }

    @Provides
    fun provideAppLogic(dbRepository: DBRepository): AppLogic {
        return AppLogicImpl(dbRepository)
    }

}