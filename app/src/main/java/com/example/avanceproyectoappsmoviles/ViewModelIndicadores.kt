package com.example.avanceproyectoappsmoviles

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ViewModelIndicadores : ViewModel() {
    val actividades = listOf("¿Tienes tos?", "¿Vomitaste hoy?", "¿Tuviste fiebre hoy?", "¿Hiciste diarrea hoy?", "¿Te duele la garganta?", "¿Te duele el cuerpo?")
    var estadosChecked =
        mutableStateListOf<Boolean>().apply { addAll(List(actividades.size) { false }) }
    var mensajeResultado by mutableStateOf("")
    var mostrarResultado by mutableStateOf(false)

    fun actualizarCheckbox(index: Int, checked: Boolean) {
        estadosChecked[index] = checked
    }

    fun calcularResultado() {
        if (estadosChecked.size < 6) {
            mensajeResultado = "Error: Faltan síntomas en la lista."
            mostrarResultado = true
            return
        }

        val tieneToz = estadosChecked[0]
        val tieneVomito = estadosChecked[1]
        val tieneFiebre = estadosChecked[2]
        val tieneDiarrea = estadosChecked[3]
        val tieneDolorGarganta = estadosChecked[4]
        val tieneDolorCuerpo = estadosChecked[5]

        mensajeResultado = when {
            estadosChecked.all { it } -> "Tienes cáncer"
            tieneFiebre && tieneDolorCuerpo -> "Tienes COVID"
            tieneToz && tieneDolorGarganta -> "Tienes gripe"
            tieneVomito && tieneDiarrea -> "Tienes gastroenteritis"
            tieneFiebre && tieneDolorGarganta -> "Tienes neumonía"
            estadosChecked.none { it } -> "No estás enfermo."
            else -> "No se detectó una enfermedad específica."
        }
        mostrarResultado = true
    }
}