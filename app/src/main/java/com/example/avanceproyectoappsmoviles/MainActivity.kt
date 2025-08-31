package com.example.avanceproyectoappsmoviles

import android.R.attr.title
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color

import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.avanceproyectoappsmoviles.navigation.BarraNavegacion
import com.example.avanceproyectoappsmoviles.ui.theme.AvanceProyectoAppsMovilesTheme
import org.w3c.dom.Text

val InterFont = FontFamily(
    Font(R.font.intervariable),
    Font(R.font.intertalic)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AvanceProyectoAppsMovilesTheme  {
                        BarraNavegacion()

//                    SintomasCrearMostrar(
//                        sintomasViewModel = SintomasViewModel()
//                    )


                }
            }
        }
    }


data class SintomasData(val text: String)

@Composable
fun SintomasCrearMostrar(modifier: Modifier = Modifier, sintomasViewModel: SintomasViewModel ){
    var input by rememberSaveable { mutableStateOf("") }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)


    )
    {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Mis Sìntomas",
                color = Color(0xFF008959),
                fontFamily = InterFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
            IconButton(
                onClick = {
                    sintomasViewModel.addSintomas(input)
                    input = ""
                },

                enabled = input.isNotEmpty()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.botonaddsintoma),
                    contentDescription = "Nuevo Sintoma",
                    modifier = Modifier.size(30.dp),
                    tint = Color.Unspecified
                )


            }
        }

        TextField(
            value = input,
            onValueChange = {input = it},
            label = { Text("Introduce tu sintoma")},
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.White,
                    shape = RoundedCornerShape(4.dp)),
            colors = TextFieldDefaults.colors(
                //Esto es para la rayita del TextField
                //Monse no voy a moverTodo nomas para que tenga un borde
                focusedIndicatorColor = Color(0x61085339),
                unfocusedIndicatorColor = Color(0x61085339),
                disabledIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
                ),
        )
        Spacer(modifier = Modifier.size(10.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(0.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(
                sintomasViewModel.sintomas
            ){
                item  ->
                Card (
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFD8E3DF)
                    ),
                    shape = RoundedCornerShape(4.dp)
                ){
                    Text(
                        text = item.text,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 18.sp
                    )
                }
            }
        }

    }
}
//@Preview(showBackground = true,
//    showSystemUi = true)
//@Composable
//fun Preview(){
//    AvanceProyectoAppsMovilesTheme {
//        SintomasCrearMostrar(
//            sintomasViewModel = SintomasViewModel()
//        )
//    }
//
//
//}

