package cl.mmr.mmexamenfinal.Datos

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cl.mmr.mmexamenfinal.Datos.Mediciones
import cl.mmr.mmexamenfinal.Datos.Medidor
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class ViewModelMediciones : ViewModel() {

    var medidorNumber by mutableStateOf("")
    var medidorValue by mutableStateOf("")
    var selectedTipoMedidor by mutableStateOf(Mediciones.Agua)
    var mediciones = MutableStateFlow<List<Medidor>>(emptyList())
    var medidorDate by mutableStateOf(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE))
    fun agregarMedicion(medidorDate: String) {

        val valor = medidorValue.toDoubleOrNull()
        if (valor != null) {

            val nuevaMedicion = Medidor(
                tipo = selectedTipoMedidor,
                valor = valor,
                fecha = LocalDate.now()
            )
            val listaActualizada = mediciones.value.toMutableList()
            listaActualizada.add(nuevaMedicion)
            mediciones.value = listaActualizada
        } else {
            // Manejar error de conversión de valor aquí
            println("Error: Valor de medición no es un número válido.")
        }
    }

    fun agregarMedicion() {
        TODO("Not yet implemented")
    }
}