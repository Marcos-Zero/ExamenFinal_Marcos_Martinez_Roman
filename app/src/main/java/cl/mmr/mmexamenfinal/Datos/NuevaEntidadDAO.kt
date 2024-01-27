package cl.mmr.mmexamenfinal.Datos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NuevaEntidadDAO {
    @Insert
    suspend fun insertar(nuevaEntidad: NuevaEntidad): Long

    @Update
    suspend fun actualizar(nuevaEntidad: NuevaEntidad)

    @Delete
    suspend fun eliminar(nuevaEntidad: NuevaEntidad)

    @Query("SELECT * FROM nuevaentidad")
    fun obtenerTodos(): Flow<List<NuevaEntidad>>


    @Query("SELECT * FROM nuevaentidad WHERE GastoId = :tipoGasto")
    fun buscarPorTipo(tipoGasto: Mediciones): Flow<List<NuevaEntidad>>
}