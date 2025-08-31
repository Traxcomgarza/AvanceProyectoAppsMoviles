package com.example.avanceproyectoappsmoviles

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ViewModelRegistroDiario : ViewModel() {
    // Lista de tareas (texto + checked)
    var tareas = mutableStateListOf<Pair<String, Boolean>>()
    var nuevaTarea by mutableStateOf("")

    fun onNuevaTareaChange(text: String) {
        nuevaTarea = text
    }

    fun agregarTarea() {
        if (nuevaTarea.isNotBlank()) {
            tareas.add(nuevaTarea to false)
            nuevaTarea = ""
        }
    }

    fun actualizarCheck(idx: Int, checked: Boolean) {
        tareas[idx] = tareas[idx].copy(second = checked)
    }
    fun eliminarTarea(idx: Int) {
        tareas.removeAt(idx)
    }
}