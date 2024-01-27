package cl.mmr.mmexamenfinal.Datos


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [NuevaEntidad::class], version = 1)
@TypeConverters(LocalDateConverter::class)

abstract class BaseDeDatos : RoomDatabase(){
    abstract fun NuevaEntidadDAO(): NuevaEntidadDAO
}