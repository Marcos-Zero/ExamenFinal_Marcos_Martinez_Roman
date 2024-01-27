package cl.mmr.mmexamenfinal.Datos

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class NuevaEntidad (
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    val GastoId: Long,
    val valor: Double,
    val fecha: String
)