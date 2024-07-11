package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [IntakeEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class WaterIntakeDatabase: RoomDatabase() {
    abstract val intakeDao: IntakeDao

}