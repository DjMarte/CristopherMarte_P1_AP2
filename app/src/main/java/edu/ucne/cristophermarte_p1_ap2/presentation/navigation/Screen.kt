package edu.ucne.cristophermarte_p1_ap2.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen{
    @Serializable
    data object Home : Screen()
}