package com.example.avanceproyectoappsmoviles

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class SintomasViewModel: ViewModel() {
    val sintomas = mutableStateListOf<SintomasData>()

    fun addSintomas(text:String){
        sintomas.add(SintomasData(text))
    }

//    fun removerSintomas(text: String): Boolean{
//        return sintomas.remove(SintomasData(text))
//    }
}