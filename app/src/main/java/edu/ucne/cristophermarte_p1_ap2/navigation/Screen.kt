package edu.ucne.cristophermarte_p1_ap2.navigation

import kotlinx.serialization.Serializable

sealed class Screen{
    @Serializable
    data object Home : Screen()

    @Serializable
    data object SistemaListScreen : Screen()

    @Serializable
    data class Sistema(val sistemaId: Int) : Screen()
}