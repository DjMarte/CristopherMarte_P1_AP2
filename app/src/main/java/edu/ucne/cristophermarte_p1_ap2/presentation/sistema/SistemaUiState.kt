package edu.ucne.cristophermarte_p1_ap2.presentation.sistema

import edu.ucne.cristophermarte_p1_ap2.data.local.entity.SistemaEntity

data class SistemaUiState(
    val sistemaId: Int? = null,
    val nombre: String = "",
    val precio: String = "",
    val errorMessage: String? = null,
    val listaSistema: List<SistemaEntity> = emptyList()
)