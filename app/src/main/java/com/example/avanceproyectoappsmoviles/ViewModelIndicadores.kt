package com.example.avanceproyectoappsmoviles

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ViewModelIndicadores : ViewModel() {
    val actividades = listOf("¿Tosiste el dia de hoy?", "¿Vomitaste hoy?", "¿Tuviste fiebre hoy?","¿Hiciste diarrea hoy?", "¿Te dolio la garganta hoy?", "¿Te dolio el cuerpo hoy?", "¿Te dolio la cabeza hoy?", "¿Te dio escalofrios hoy?", "¿Te dio congestion nasal hoy?"
    )
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
        val tienetos = estadosChecked[0]
        val tienevomito = estadosChecked[1]
        val tienefiebre = estadosChecked[2]
        val tienediarrea = estadosChecked[3]
        val tienedolorgarganta = estadosChecked[4]
        val tienedolorcuerpo = estadosChecked[5]
        val tienedolorcabeza = estadosChecked[6]
        val tieneescalofrios = estadosChecked[7]
        val tienecongestion = estadosChecked[8]

        mensajeResultado = when {
            estadosChecked.all { it } -> "Tienes cáncer"
            tienefiebre && tienedolorcuerpo && tieneescalofrios -> "Tienes influenza"
            tienetos && tienedolorgarganta && tienecongestion -> "Tienes resfriado común"
            tienevomito && tienediarrea -> "Tienes gastroenteritis"
            tienefiebre && tienedolorgarganta -> "Tienes neumonía"
            tienefiebre && tienedolorcuerpo -> "Tienes COVID"
            tienedolorcabeza && tienecongestion -> "Tienes sinusitis"
            tienedolorcabeza && tienetos -> "Tienes bronquitis"
            tienetos && tienedolorgarganta -> "Tienes gripe"
            estadosChecked.none { it } -> "No estás enfermo."
            else -> "No se detectó una enfermedad específica."
        }

        mostrarResultado = true
    }
}