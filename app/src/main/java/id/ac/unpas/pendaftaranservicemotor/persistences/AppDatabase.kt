package id.ac.unpas.pendaftaranservicemotor.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.pendaftaranservicemotor.model.ServiceMotor

@Database(entities = [ServiceMotor::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun serviceMotorDao(): ServiceMotorDao
}
