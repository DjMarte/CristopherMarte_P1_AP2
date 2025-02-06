package edu.ucne.cristophermarte_p1_ap2.presentation.sistema

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.cristophermarte_p1_ap2.data.local.entity.SistemaEntity
import edu.ucne.cristophermarte_p1_ap2.data.repository.SistemaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SistemaViewModel @Inject constructor(
    private val sistemaRepository: SistemaRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(SistemaUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getSistemas()
    }

    fun save() {
        viewModelScope.launch {
            val nombre = _uiState.value.nombre

            val nombre_Expresion_Regular = "^[A-Za-z\\s']+\$".toRegex()

            if (nombre.isBlank()) {
                _uiState.update {
                    it.copy(errorMessage = "Campo nombre requerido")
                }
                return@launch
            } else if (!nombre.matches(nombre_Expresion_Regular)) {
                _uiState.update {
                    it.copy(errorMessage = "El nombre no debe contener números o caracteres especiales")
                }
                return@launch
            } else {
                sistemaRepository.saveSistema(_uiState.value.toEntity())
                new()
            }
        }
    }

    fun find(sistemaId: Int) {
        viewModelScope.launch {
            if (sistemaId > 0) {
                val sistema = sistemaRepository.find(sistemaId)
                if (sistema != null) {
                    _uiState.update {
                        it.copy(
                            sistemaId = sistema.sistemaId,
                            nombre = sistema.nombre
                        )
                    }
                }
            }
        }
    }

    fun new() {
        _uiState.value = SistemaUiState()
    }

    fun delete() {
        viewModelScope.launch {
            sistemaRepository.delete(uiState.value.toEntity())
        }
    }

    fun onNombreChange(nombre: String) {
        _uiState.update {
            it.copy(
                nombre = nombre
            )
        }
    }


    private fun getSistemas() {
        viewModelScope.launch {
            sistemaRepository.getAll().collect { listaSistema ->
                _uiState.update {
                    it.copy(listaSistema = listaSistema)
                }
            }
        }
    }
}
fun SistemaUiState.toEntity() = SistemaEntity(
    sistemaId = this.sistemaId,
    nombre = this.nombre
)