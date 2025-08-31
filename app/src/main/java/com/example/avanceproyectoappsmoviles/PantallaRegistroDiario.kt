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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PantallaRegistroDiario(
    registroDiarioViewModel: ViewModelRegistroDiario = viewModel()
) {
    val tareas = registroDiarioViewModel.tareas
    val nuevaTarea = registroDiarioViewModel.nuevaTarea

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color(0xFFD8E3DF)),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "CheckList",
                    fontFamily = InterFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = Color(0xFF178965),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(18.dp)
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Mi Lista",
                        fontFamily = InterFont,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF178965),
                        fontSize = 25.sp
                    )
                    Spacer(Modifier.weight(1f))
                    IconButton(
                        onClick = { registroDiarioViewModel.agregarTarea() },
                        enabled = nuevaTarea.isNotBlank()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.botonaddsintoma),
                            contentDescription = "Nuevo",
                            modifier = Modifier.size(30.dp),
                            tint = Color.Unspecified
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    value = nuevaTarea,
                    onValueChange = { registroDiarioViewModel.onNuevaTareaChange(it) },
                    placeholder = { Text("Texto") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(4.dp)
                        ),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color(0x61085339),
                        unfocusedIndicatorColor = Color(0x61085339),
                        disabledIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                tareas.forEachIndexed { idx, par ->
                    val (texto, checked) = par
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 2.dp)
                            .fillMaxWidth()
                            .background(
                                if (checked) Color(0xFFD8E3DF) else Color(0xFFD8E3DF),
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
    }
}