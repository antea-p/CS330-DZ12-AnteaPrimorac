package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.db.TransactionDao
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.db.TransactionDatabase
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.repository.DBRepository
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.repository.DBRepositoryImpl
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.repository.TransactionMapper
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain.AppLogic
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domain.AppLogicImpl
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
    fun provideTransactionDao(database: TransactionDatabase): TransactionDao {
        return database.transactionDao
    }

    @Provides
    @Singleton
    fun provideTransactionDatabase(app: Application): TransactionDatabase {
        return Room.databaseBuilder(
            app,
            TransactionDatabase::class.java,
            "transaction_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDBRepository(transactionDao: TransactionDao, mapper: TransactionMapper): DBRepository {
        return DBRepositoryImpl(transactionDao, mapper)
    }

    @Provides
    fun provideTransactionMapper(): TransactionMapper {
        return TransactionMapper()
    }

    @Provides
    fun provideAppLogic(dbRepository: DBRepository): AppLogic {
        return AppLogicImpl(dbRepository)
    }

}