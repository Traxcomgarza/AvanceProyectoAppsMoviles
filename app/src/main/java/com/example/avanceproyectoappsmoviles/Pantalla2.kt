import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.collections.plus

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Checklist()
        }
    }
}
@Composable
fun IngresarActividad(
    actividad: String,
    onActividadChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = actividad,
        onValueChange = onActividadChange,
        label = { Text("Tarea") },
        placeholder = { Text("Ingresar Tarea") },
        modifier = modifier
    )
}
@Composable
fun Checklist() {
    var nuevaActividad by remember { mutableStateOf("") }
    var actividades by remember { mutableStateOf(listOf<Pair<String, Boolean>>()) }
    var tareasAcumuladas by remember { mutableStateOf(listOf<String>()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IngresarActividad(
                    actividad = nuevaActividad,
                    onActividadChange = { nuevaActividad = it },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    if (nuevaActividad.isNotBlank()) {
                        actividades = actividades + (nuevaActividad to false)
                        nuevaActividad = ""
                    }
                }) {
                    Text("Agregar")
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            if (tareasAcumuladas.isNotEmpty()) {
                Text("ya realizaste: " + tareasAcumuladas.joinToString(", "))
                Spacer(modifier = Modifier.height(10.dp))
            }

            actividades.forEachIndexed { idx, (act, checked) ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 6.dp)
                ) {
                    Checkbox(
                        checked = checked,
                        onCheckedChange = { check ->
                            actividades = actividades.toMutableList().also {
                                it[idx] = it[idx].copy(second = check)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = act)
                }
            }
        }

        Button(
            onClick = {

                val seleccionadas = mutableListOf<String>()
                val nuevasActividades = mutableListOf<Pair<String, Boolean>>()
                for ((nombre, checked) in actividades) {
                    if (checked) {
                        seleccionadas.add(nombre)
                        nuevasActividades.add(nombre to false)
                    } else {
                        nuevasActividades.add(nombre to false)
                    }
                }
                if (seleccionadas.isNotEmpty()) {
                    tareasAcumuladas = tareasAcumuladas + seleccionadas
                    actividades = nuevasActividades
                } else {
                    actividades = nuevasActividades
                }
            },
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
        ) {
            Text("Realizado")
        }
    }
}