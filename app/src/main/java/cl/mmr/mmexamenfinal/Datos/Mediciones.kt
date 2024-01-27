package cl.mmr.mmexamenfinal.Datos

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
enum class Mediciones {
    Agua,
    Luz,
    Gas
}

open class Medidor(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tipo: Mediciones ,
    val valor: Double,
    val fecha: LocalDate)