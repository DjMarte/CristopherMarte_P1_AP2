package edu.ucne.cristophermarte_p1_ap2.presentation.sistema

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun SistemaScreen(
    viewModel: SistemaViewModel = hiltViewModel(),
    goBackToList: () -> Unit,
    sistemaId: Int
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    SistemaListBodyScreen(
        sistemaId = sistemaId,
        viewModel,
        uiState = uiState,
        goBackToList
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SistemaListBodyScreen(
    sistemaId: Int,
    viewModel: SistemaViewModel,
    uiState: SistemaUiState,
    goBackToList: () -> Unit
) {
    LaunchedEffect(sistemaId) {
        if (sistemaId > 0) viewModel.find(sistemaId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = if (sistemaId > 0) "Editar Sistema" else "Agregar Sistema")
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            ElevatedCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    // Campo Nombre
                    OutlinedTextField(
                        label = { Text(text = "Nombres") },
                        value = uiState.nombre,
                        onValueChange = viewModel::onNombreChange,
                        modifier = Modifier.fillMaxWidth()
                    )
                    // Espacio para el mensaje de error
                    Spacer(modifier = Modifier.padding(2.dp))
                    uiState.errorMessage?.let {
                        Text(text = it, color = Color.Red)
                    }

                    // Boton Nuevo
                    if (sistemaId <= 0) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Boton Nuevo
                            OutlinedButton(onClick = {
                                viewModel.new()
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "New button",
                                    tint = Color.Blue
                                )
                                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                                Text(text = "Nuevo")
                            }
                            // Boton Guardar
                            OutlinedButton(
                                onClick = {
                                    viewModel.save()
                                }) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Save button",
                                    tint = Color.Green
                                )
                                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                                Text(text = "Guardar")
                            }
                        }

                    } else {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Boton Modificar
                            OutlinedButton(
                                onClick = {
                                    viewModel.save()
                                    goBackToList()
                                }) {
                                Icon(
                                    imageVector = Icons.Default.Create,
                                    contentDescription = "update button",
                                    tint = Color.Green
                                )
                                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                                Text(text = "Modificar")
                            }

                            // Boton Eliminar
                            OutlinedButton(onClick = {
                                viewModel.delete()
                                goBackToList()
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Eliminar",
                                    tint = Color.Red
                                )
                                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                                Text(text = "Eliminar")
                            }
                        }
                    }
                }
            }
        }
    }
}