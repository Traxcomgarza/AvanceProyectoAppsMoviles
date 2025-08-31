package com.example.avanceproyectoappsmoviles


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextField


data class Recordatorio(
    val texto:String,
    val hora:String
)


//ESTE LO ESTAMOS USANDO PARA LA PLANTILLA DEL RELOJ
@OptIn(ExperimentalMaterial3Api::class)
// [START android_compose_components_input_usestate]
@Composable
fun InputUseStateExample(
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit,
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    Column {
        TimeInput(
            state = timePickerState,
        )
        Button(onClick = onDismiss) {
            Text("Cancelar")
        }
        Button(onClick = { onConfirm(timePickerState) }) {
            Text("Confirmar")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaAgregarRecordatorio(
    onAgregar: (Recordatorio) -> Unit,
    onBack: () -> Unit
) {
    var showPicker by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    var hora by remember { mutableStateOf("") }
    val formatter = remember { SimpleDateFormat("HH:mm", Locale.getDefault()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Nuevo recordatorio")
        Spacer(Modifier.height(16.dp))
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Recordatorio") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        OutlinedButton(onClick = { showPicker = true }) {
            Text(if (hora.isEmpty()) "Seleccionar hora" else "Hora: $hora")
        }
        if (showPicker) {
            InputUseStateExample(
                onDismiss = { showPicker = false },
                onConfirm = { timeState ->
                    showPicker = false
                    val cal = Calendar.getInstance()
                    cal.set(Calendar.HOUR_OF_DAY, timeState.hour)
                    cal.set(Calendar.MINUTE, timeState.minute)
                    hora = formatter.format(cal.time)
                }
            )
        }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
                if (text.isNotBlank() && hora.isNotBlank()) {
                    onAgregar(Recordatorio(text, hora))
                    onBack()
                }
            },
            enabled = text.isNotBlank() && hora.isNotBlank()
        ) {
            Text("Confirmar")
        }
        Spacer(Modifier.height(16.dp))
        OutlinedButton(onClick = onBack) {
            Text("Cancelar")
        }
    }
}


@Composable
fun PantallaListaRecordatorios(
    recordatorios: List<Recordatorio>,
    onAgregarClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (recordatorios.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 80.dp), // este es el espacio para el boton w
                contentAlignment = Alignment.Center
            ) {
                Text("No hay recordatorios")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(bottom = 80.dp), // este es el espacio para el boton otra vez w
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(recordatorios) { rec ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(Modifier.padding(16.dp)) {
                            Text(rec.texto)
                            Text("Hora: ${rec.hora}", style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
            }
        }


        Button(
            onClick = onAgregarClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 104.dp) // Este tambien es el espacio para el boton XD para q no se enpalme con el nav bar
        ) {
            Text("Agregar recordatorio")
        }
    }
}








//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun PantallaRecordatorio(
//    onBack: () -> Unit
//) {
//    var showPicker by remember { mutableStateOf(false) }
//    var text by remember { mutableStateOf("") }
//    var selectedTime by remember { mutableStateOf<TimePickerState?>(null) }
//    val formatter = remember { SimpleDateFormat("HH:mm", Locale.getDefault()) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(32.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text("Pantalla Recordatorio")
//
//        Spacer(Modifier.height(16.dp))
//
//        // TextField para escribir y mostrar el mensaje
//        TextField(
//            value = text,
//            onValueChange = { text = it },
//            label = { Text("Recordatorio") },
//            singleLine = true,
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(Modifier.height(16.dp))
//
//        Button(onClick = { showPicker = true }) {
//            Text("Seleccionar hora")
//        }
//
//        if (showPicker) {
//            // Este composable debe estar bien importado
//            InputUseStateExample(
//                onDismiss = { showPicker = false }, // No cambia el texto, solo cierra
//                onConfirm = { timeState ->         // Cambia el texto al confirmar
//                    selectedTime = timeState
//                    showPicker = false
//
//                    val cal = Calendar.getInstance()
//                    cal.set(Calendar.HOUR_OF_DAY, timeState.hour)
//                    cal.set(Calendar.MINUTE, timeState.minute)
//                    val hora = formatter.format(cal.time)
//                    // Actualiza o inserta la hora en el texto
//                    val regex = Regex(":\\s*\\d{2}:\\d{2}\$")
//                    text = if (text.isBlank()) {
//                        "Tomar pastilla a las: $hora"
//                    } else if (regex.containsMatchIn(text)) {
//                        text.replace(regex, ": $hora")
//                    } else {
//                        "$text a las: $hora"
//                    }
//                }
//            )
//        }
//
//        Spacer(Modifier.height(16.dp))
//
//        Button(onClick = onBack) {
//            Text("Volver")
//        }
//    }
//}



//@Composable
//fun PantallaRecordatorio(modifier: Modifier = Modifier){
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text("Pantalla Recordatorio")
//    }
//}