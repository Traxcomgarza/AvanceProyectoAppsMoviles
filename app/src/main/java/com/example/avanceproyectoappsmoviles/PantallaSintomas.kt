package com.example.avanceproyectoappsmoviles

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantallaSintomas(
    viewModel: SintomasViewModel,
    modifier: Modifier = Modifier
){
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFD8E3DF))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Hola Monse",
                    color = Color(0xFF008959),
                    fontFamily = InterFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                    //Revisar la fuente que monse pidio
                )
            }

            SintomasCrearMostrar(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                sintomasViewModel = viewModel
            )
        }
    }
}