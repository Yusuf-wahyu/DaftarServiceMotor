package id.ac.unpas.pendaftaranservicemotor.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ServiceMotor(
    @PrimaryKey val id: String,
    val nama: String,
    val nopolisi: String,
    val tanggal: String,
    val kilometer: String
)