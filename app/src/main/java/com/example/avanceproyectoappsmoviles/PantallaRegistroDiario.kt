package com.example.avanceproyectoappsmoviles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PantallaRegistroDiario(
    registroDiarioViewModel: ViewModelRegistroDiario = viewModel()
) {
    val tareas = registroDiarioViewModel.tareas
    val nuevaTarea = registroDiarioViewModel.nuevaTarea

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
                text = "CheckList",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF178965),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))

        // "Mi Lista" y botón +
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                "Mi Lista",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF178965)
            )
            Spacer(Modifier.weight(1f))
            IconButton(
                onClick = { registroDiarioViewModel.agregarTarea() },
                enabled = nuevaTarea.isNotBlank()
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar",
                    tint = Color(0xFF178965)
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))

        // Input field
        OutlinedTextField(
            value = nuevaTarea,
            onValueChange = { registroDiarioViewModel.onNuevaTareaChange(it) },
            placeholder = { Text("Texto") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Lista de tareas
        tareas.forEachIndexed { idx, par ->
            val (texto, checked) = par
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 2.dp)
                    .fillMaxWidth()
                    .background(
                        if (checked) Color(0xFFD8E3DF) else Color.White,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .height(40.dp)
            ) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = { registroDiarioViewModel.actualizarCheck(idx, it) },
                    colors = CheckboxDefaults.colors(checkedColor = Color(0xFF178965))
                )
                Text(
                    text = texto,
                    color = Color(0xFF3E6259),
                    modifier = Modifier.padding(start = 4.dp)
                )
                Spacer(modifier = Modifier.weight(1f))

            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}