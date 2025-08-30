package com.example.avanceproyectoappsmoviles

import androidx.lifecycle.ViewModel

class SintomasViewModel: ViewModel() {
    val sintomas = mutableListOf<SintomasData>()

    fun addSintomas(text:String){
        sintomas.add(SintomasData(text))
    }

    fun removerSintomas(text: String): Boolean{
        return sintomas.remove(SintomasData(text))
    }
}