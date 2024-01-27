package cl.mmr.mmexamenfinal


import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.mmr.mmexamenfinal.Datos.Mediciones
import cl.mmr.mmexamenfinal.Datos.ViewModelMediciones
import java.time.format.DateTimeFormatter


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(navController: NavController, viewModel: ViewModelMediciones) {
    val mediciones by viewModel.mediciones.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = { navController.navigate("details") }) {
            Text("Añadir Medición")
        }

        LazyColumn {
            items(mediciones) { medidor ->
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = "Tipo: ${medidor.tipo.name}")
                Text(text = "Valor: ${medidor.valor}")
                Text(text = "Fecha: ${medidor.fecha.format(DateTimeFormatter.ISO_LOCAL_DATE)}")
                Spacer(modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailScreen(navController: NavController, viewModel: ViewModelMediciones) {


    Column {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.gas),
                contentDescription = "Logo"
            )
        }
    }

    Column {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.luz),
                contentDescription = "Logo"
            )
        }
    }
    Column {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.agua),
                contentDescription = "Logo"
            )
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Registro Medidor",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = viewModel.medidorNumber,
            onValueChange = { viewModel.medidorNumber = it },
            label = { Text("Medidor") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.medidorDate,
            onValueChange = { viewModel.medidorDate = it },
            label = { Text("Fecha") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column {
            Text(text = "Medidor de:")

            Mediciones.entries.forEach { tipo ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (tipo == viewModel.selectedTipoMedidor),
                            onClick = { viewModel.selectedTipoMedidor = tipo }
                        )
                        .padding(vertical = 8.dp)
                ) {
                    RadioButton(
                        selected = (tipo == viewModel.selectedTipoMedidor),
                        onClick = { viewModel.selectedTipoMedidor = tipo }
                    )
                    Text(text = tipo.name, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = {
                viewModel.agregarMedicion()
                navController.navigate("home")  // Navega de vuelta a la pantalla de inicio
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Registrar medición")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: ViewModelMediciones = viewModel()
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController, viewModel) }
        composable("details") { DetailScreen(navController, viewModel) }
        composable("medicionesList") { MedicionesListasScreen(viewModel) }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MedicionesListasScreen(viewModel: ViewModelMediciones) {
    val mediciones by viewModel.mediciones.collectAsState()

    LazyColumn {
        items(mediciones) { medidor ->
            Row(modifier = Modifier.padding(8.dp)) {
                Text(text = medidor.tipo.name, modifier = Modifier.weight(1f))
                Text(text = "${medidor.valor}", modifier = Modifier.weight(1f))
                Text(
                    text = medidor.fecha.format(DateTimeFormatter.ISO_LOCAL_DATE),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
