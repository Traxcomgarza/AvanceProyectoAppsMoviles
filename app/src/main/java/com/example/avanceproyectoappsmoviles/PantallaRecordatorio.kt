package com.example.avanceproyectoappsmoviles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

data class Recordatorio(
    val texto: String,
    val hora: String
)

@OptIn(ExperimentalMaterial3Api::class)
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
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TimeInput(state = timePickerState)
        Spacer(Modifier.height(12.dp))
        Button(onClick = { onConfirm(timePickerState) }) { Text("Confirmar") }
        Spacer(Modifier.height(8.dp))
        OutlinedButton(onClick = onDismiss) { Text("Cancelar") }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaAgregarRecordatorio(
    onAgregar: (Recordatorio) -> Unit,
    onBack: () -> Unit
) {
    val formatter = remember { SimpleDateFormat("HH:mm", Locale.getDefault()) }
    var texto by remember { mutableStateOf("") }
    val now = remember { Calendar.getInstance() }
    val timePickerState = rememberTimePickerState(
        initialHour = now.get(Calendar.HOUR_OF_DAY),
        initialMinute = now.get(Calendar.MINUTE),
        is24Hour = true
    )

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color(0xFFD8E3DF)),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Recordatorios",
                    color = Color(0xFF178965),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Nuevo recordatorio",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = Color(0xFF178965)
            )

            Spacer(Modifier.height(12.dp))

            TextField(
                value = texto,
                onValueChange = { texto = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                singleLine = true,
                colors = TextFieldDefaults.colors(focusedIndicatorColor = Color(0x61085339),
                    unfocusedIndicatorColor = Color(0x61085339),
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                ),
                placeholder = { Text("Agrega un recordatorio", color = Color(0xFF3E6259)) }
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Seleccionar hora",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = Color(0xFF178965)
            )

            Spacer(Modifier.height(8.dp))

            TimeInput(state = timePickerState)

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    val cal = Calendar.getInstance().apply {
                        set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                        set(Calendar.MINUTE, timePickerState.minute)
                        set(Calendar.SECOND, 0)
                        set(Calendar.MILLISECOND, 0)
                    }
                    val horaFormateada = formatter.format(cal.time)
                    onAgregar(Recordatorio(texto.trim(), horaFormateada))
                    onBack()
                },
                enabled = texto.isNotBlank(),
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF178965)
                )
            ) {
                Text("Confirmar")
            }

            Spacer(Modifier.height(12.dp))

            OutlinedButton(
                onClick = onBack,
                shape = MaterialTheme.shapes.large
            ) {
                Text("Cancelar", color = Color(0xFF178965))
            }
        }
    }
}

@Composable
fun PantallaListaRecordatorios(
    recordatorios: List<Recordatorio>,
    onAgregarClick: () -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color(0xFFD8E3DF)),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Recordatorios",
                    fontSize = 25.sp,
                    color = Color(0xFF178965),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                if (recordatorios.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 80.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No hay recordatorios", color = Color(0xFF3E6259))
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .padding(bottom = 80.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(recordatorios) { rec ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFFD8E3DF)),
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                            ) {
                                Column(Modifier.padding(16.dp)) {
                                    Text(rec.texto, color = Color(0xFF3E6259))
                                    Text(
                                        "Hora: ${rec.hora}",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = Color(0xFF178965),

                                    )
                                }
                            }
                        }
                    }
                }

                Button(
                    onClick = onAgregarClick,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 104.dp),
                    shape = MaterialTheme.shapes.large,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFF178965)
                    )
                ) {
                    Text("Agregar recordatorio")
                }
            }
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