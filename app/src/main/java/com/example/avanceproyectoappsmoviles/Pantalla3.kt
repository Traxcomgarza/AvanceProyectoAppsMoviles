package com.example.avanceproyectoappsmoviles
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Checklist()
        }
    }
}

@Composable
fun Checklist() {
    val actividades = listOf("tienes toz?", "vomitaste hoy?", "tuviste fiebre hoy?", "hiciste diarrea hoy?", "te duele la garganta?","te duele el cuerpo?")
    var mostrarResultado by remember { mutableStateOf(false) }
    var mensajeResultado by remember { mutableStateOf("") }
    var estadosChecked by remember { mutableStateOf(List(actividades.size) { false }) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopStart)
                .fillMaxHeight()
        ) {
            actividades.forEachIndexed { indice, nombreActividad ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = estadosChecked[indice],
                        onCheckedChange = { marcado ->
                            estadosChecked =
                                estadosChecked.toMutableList().also { it[indice] = marcado }
                        }
                    )
                    Text(nombreActividad)
                    if (indice == 0) {
                        Image(
                            painter = painterResource(id = R.drawable.d3f5b6e71b879161d37d4ae578230135),
                            contentDescription ="imagen",
                            modifier = Modifier.size(36.dp)
                        )
                    }
                    if (indice == 1) {
                        Image(
                            painter = painterResource(id = R.drawable.vomito),
                            contentDescription = "imagen",
                            modifier = Modifier.size(36.dp)
                        )
                    }
                    if (indice == 2) {
                        Image(
                            painter = painterResource(id = R.drawable.temperatura),
                            contentDescription = "imagen",
                            modifier = Modifier.size(36.dp)

                        )
                    }
                    if (indice == 3) {
                        Image(
                            painter = painterResource(id = R.drawable.bano),
                            contentDescription = "imagen",
                            modifier = Modifier.size(36.dp)

                        )
                    }
                }
            }
            if (mostrarResultado) {
                Text(mensajeResultado)
            }
        }
        Button(
            onClick = {
                val tieneToz = estadosChecked[0]
                val tieneVomito = estadosChecked[1]
                val tieneFiebre = estadosChecked[2]
                val tieneDiarrea = estadosChecked[3]
                val tieneDolorGarganta = estadosChecked[4]
                val tieneDolorCuerpo = estadosChecked[5]

                mensajeResultado = when {
                    tieneFiebre && tieneDolorCuerpo -> "Tienes COVID"
                    tieneToz && tieneDolorGarganta -> "Tienes gripe"
                    estadosChecked.none { it } -> "no estas enfermo."
                    else -> "No se detectó una enfermedad específica."
                }

                mostrarResultado = true
            },
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
        ) {
            Text("Enviar")
        }
    }
}