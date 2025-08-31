package com.example.avanceproyectoappsmoviles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PantallaIndicadores(viewModel: ViewModelIndicadores = viewModel()) {
    val actividades = viewModel.actividades
    val estadosChecked = viewModel.estadosChecked
    val mensajeResultado = viewModel.mensajeResultado
    val mostrarResultado = viewModel.mostrarResultado

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7F6))
    ) {
        // Header
        Box(
            Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color(0xFFD8E3DF)),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Indicadores",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFF178965),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Subtítulo y explicación
        Text(
            "Evaluación de síntomas",
            color = Color(0xFF178965),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            "Selecciona los síntomas que presentas y presiona enviar para ver el resultado.",
            color = Color(0xFF3E6259),
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Checklist
        Column(
            Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            actividades.forEachIndexed { indice, nombreActividad ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            if (estadosChecked[indice]) Color(0xFFD8E3DF) else Color.White,
                            shape = RoundedCornerShape(2.dp)
                        )
                        .padding(start = 2.dp, end = 2.dp)
                        .height(40.dp)
                ) {
                    Checkbox(
                        checked = estadosChecked[indice],
                        onCheckedChange = { marcado ->
                            viewModel.actualizarCheckbox(indice, marcado)
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF178965),
                            uncheckedColor = Color(0xFF178965)
                        )
                    )
                    Text(
                        nombreActividad,
                        color = Color(0xFF3E6259)
                    )
                    // if (indice == 0) {
                    //     Image(
                    //         painter = painterResource(id = R.drawable.d3f5b6e71b879161d37d4ae578230135),
                    //         contentDescription = "imagen",
                    //         modifier = Modifier.size(36.dp)
                    //     )
                    // }
                    // if (indice == 1) {
                    //     Image(
                    //         painter = painterResource(id = R.drawable.vomito),
                    //         contentDescription = "imagen",
                    //         modifier = Modifier.size(36.dp)
                    //     )
                    // }
                    // if (indice == 2) {
                    //     Image(
                    //         painter = painterResource(id = R.drawable.temperatura),
                    //         contentDescription = "imagen",
                    //         modifier = Modifier.size(36.dp)
                    //     )
                    // }
                    // if (indice == 3) {
                    //     Image(
                    //         painter = painterResource(id = R.drawable.bano),
                    //         contentDescription = "imagen",
                    //         modifier = Modifier.size(36.dp)
                    //     )
                    // }
                }
                Spacer(modifier = Modifier.height(2.dp))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Resultado
        if (mostrarResultado) {
            Text(
                text = mensajeResultado,
                color = if (mensajeResultado.contains("No estás enfermo")) Color(0xFF178965) else Color.Red,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )
        }
        Button(
            onClick = { viewModel.calcularResultado() },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD8E3DF),
                contentColor = Color(0xFF178965)
            )
        ) {
            Text("Enviar")
        }
    }
}