package com.example.avanceproyectoappsmoviles.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.avanceproyectoappsmoviles.PantallaIndicadores
import com.example.avanceproyectoappsmoviles.PantallaRecordatorio
import com.example.avanceproyectoappsmoviles.PantallaRegistroDiario
import com.example.avanceproyectoappsmoviles.PantallaSintomas
import com.example.avanceproyectoappsmoviles.SintomasViewModel

enum class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
){
    SINTOMAS("pantallaSintomas", "Sintomas", Icons.Default.Call, "Pagina de Sintomas"),
    INDICADORES("pantallaIndicadores", "Indicadores", Icons.Default.Call, "Pagina de Indicadores"),
    RECORDATORIO("pantallaRecordatorio", "Recordatorios", Icons.Default.Call, "Pagina de Recordatorios"),
    DIARIO("pantallaDiario", "Registro Diario", Icons.Default.Call, "Pagina de Registro Diario")
}

@Composable
fun AppNavHost(navController: NavHostController,
               startDestination: Destination,
               modifier: Modifier = Modifier){
    val sintomasViewModel: SintomasViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ){
        Destination.entries.forEach { destination ->
            composable(destination.route){
                when (destination){
                    Destination.SINTOMAS -> PantallaSintomas(sintomasViewModel)
                    Destination.INDICADORES -> PantallaIndicadores()
                    Destination.RECORDATORIO -> PantallaRecordatorio()
                    Destination.DIARIO -> PantallaRegistroDiario()
                }
            }
        }
    }
}

@Preview()
@Composable
fun BarraNavegacion(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = Destination.SINTOMAS
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(
                windowInsets = NavigationBarDefaults.windowInsets,
                containerColor = Color(0xFFD8E3DF)
            ) {
                Destination.entries.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedDestination == index,
                        onClick = {
                            navController.navigate(route = destination.route)
                            selectedDestination = index
                        },
                        icon = {
                            Icon(
                                destination.icon,
                                contentDescription = destination.contentDescription
                            )
                        },
                        label = { Text(destination.label) },
                        colors = NavigationBarItemDefaults.colors(
                            unselectedIconColor = Color.LightGray
                        )
                    )
                }
            }
        }
    ) { contentPadding ->
        AppNavHost(navController, startDestination, modifier = Modifier.padding(contentPadding))
    }
}