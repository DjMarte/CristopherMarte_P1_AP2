package edu.ucne.cristophermarte_p1_ap2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import edu.ucne.cristophermarte_p1_ap2.presentation.HomeScreen
import edu.ucne.cristophermarte_p1_ap2.presentation.sistema.SistemaListScreen
import edu.ucne.cristophermarte_p1_ap2.presentation.sistema.SistemaScreen

@Composable
fun ParcialNavHost(
    navHostController: NavHostController
){
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home
    ) {
        composable<Screen.Home>{
            HomeScreen(
                goToSistemaList = {
                    navHostController.navigate(Screen.SistemaListScreen)
                }
            )
        }
        composable<Screen.SistemaListScreen>{
            SistemaListScreen(
                goToAddSistema = { navHostController.navigate(Screen.Sistema(0)) },
                goToEditSistema = { sistemaId ->
                    navHostController.navigate(Screen.Sistema(sistemaId = sistemaId))
                }
            )
        }
        composable<Screen.Sistema>{
            val sistemaId = it.toRoute<Screen.Sistema>().sistemaId
            SistemaScreen(
                sistemaId = sistemaId,
                goBackToList = { navHostController.navigateUp() }
            )
        }
    }
}