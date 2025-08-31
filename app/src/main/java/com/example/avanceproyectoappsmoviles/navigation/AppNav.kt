package com.example.avanceproyectoappsmoviles.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.avanceproyectoappsmoviles.PantallaIndicadores
import com.example.avanceproyectoappsmoviles.PantallaRegistroDiario
import com.example.avanceproyectoappsmoviles.PantallaSintomas
import com.example.avanceproyectoappsmoviles.PantallaAgregarRecordatorio
import com.example.avanceproyectoappsmoviles.PantallaListaRecordatorios
import com.example.avanceproyectoappsmoviles.Recordatorio
import com.example.avanceproyectoappsmoviles.R
import com.example.avanceproyectoappsmoviles.SintomasViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


enum class Destination(
    val route: String,
    val label: String,
    val icon: Int,
    val contentDescription: String
){
    SINTOMAS("pantallaSintomas", "Sintomas", R.drawable.sintomasicon, "Pagina de Sintomas"),
    INDICADORES("pantallaIndicadores", "Indicadores", R.drawable.indicadoresicon, "Pagina de Indicadores"),
    RECORDATORIO("pantallaRecordatorio", "Recordatorios", R.drawable.remindericon, "Pagina de Recordatorios"),
    DIARIO("pantallaDiario", "Registro Diario", R.drawable.remindericon, "Pagina de Registro Diario"),
    AGREGAR_RECORDATORIO("agregarRecordatorio", "Agregar", R.drawable.remindericon, "Agregar Recordatorio")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(navController: NavHostController,
               startDestination: Destination,
               modifier: Modifier = Modifier){

    var recordatorios by remember {mutableStateOf(listOf<Recordatorio>())}
    val sintomasViewModel: SintomasViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        Destination.entries.forEach { destination ->
            if (destination != Destination.AGREGAR_RECORDATORIO) {
                composable(destination.route) {
                    when (destination) {
                        Destination.SINTOMAS -> PantallaSintomas(sintomasViewModel)
                        Destination.INDICADORES -> PantallaIndicadores()
                        Destination.RECORDATORIO -> PantallaListaRecordatorios(
                            recordatorios = recordatorios,
                            onAgregarClick = { navController.navigate(Destination.AGREGAR_RECORDATORIO.route) }
                        )
                        Destination.DIARIO -> PantallaRegistroDiario()
                        else -> {}
                    }
                }
            }
        }
        composable(Destination.AGREGAR_RECORDATORIO.route) {
            PantallaAgregarRecordatorio(
                onAgregar = { nuevo ->
                    recordatorios = recordatorios + nuevo
                },
                onBack = { navController.popBackStack() }
            )
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
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets, containerColor = Color(0xFFD8E3DF)) {
                Destination.entries
                    .filter { it != Destination.AGREGAR_RECORDATORIO }
                    .forEachIndexed { index, destination ->
                        NavigationBarItem(
                            selected = selectedDestination == index,
                            onClick = {
                                navController.navigate(route = destination.route)
                                selectedDestination = index
                            },
                            icon = {
                                Box(
                                    modifier = Modifier
                                        .size(width = 50.dp, height = 28.dp)
                                        .background(
                                            color = Color(0xFFBFD0CD),
                                            shape = RoundedCornerShape(5.dp)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = destination.icon),
                                        contentDescription = destination.contentDescription,
                                        //Gracias a esto podemos hacer que el icono se vea negro y no solo el activo
                                        tint = Color(0xFF222222),
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            },
                            label = { Text(destination.label) },
                            colors = NavigationBarItemDefaults.colors(
                                //con esto eliminamos el indicador morado
                                indicatorColor = Color.Transparent,
                                //esto es para el color del texto
                                selectedTextColor = Color(0xFF008959),
                                unselectedTextColor = Color(0xFF008959)
                            )
                        )
                    }
            }
        }
    ) { contentPadding ->
        AppNavHost(navController, startDestination, modifier = Modifier.padding(contentPadding))
    }
}