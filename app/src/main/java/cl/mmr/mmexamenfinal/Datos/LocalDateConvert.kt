package cl.mmr.mmexamenfinal.Datos


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate

class LocalDateConverter {
    @TypeConverter
    fun LocalDate(value: LocalDate?): String? {
        return value?.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun LocalDate(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it) }
    }
}