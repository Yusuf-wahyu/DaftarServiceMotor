package id.ac.unpas.pendaftaranservicemotor.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.pendaftaranservicemotor.model.ServiceMotor

@Dao
interface ServiceMotorDao {
    @Query("SELECT * FROM ServiceMotor")
    fun loadAll(): LiveData<List<ServiceMotor>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: ServiceMotor)
}