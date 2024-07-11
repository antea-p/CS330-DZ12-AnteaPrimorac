package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.AppViewModel
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.page.AddWaterIntakeScreen
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.page.HomeScreen
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.page.WaterIntakeListScreen

@Composable
fun NavSetup(vm: AppViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val paddingValues = PaddingValues()

    NavHost(navController = navController, startDestination = NavigationRoutes.HomeScreen.route) {
        composable(route = NavigationRoutes.HomeScreen.route) {
            HomeScreen(
                addIntake = {navController.navigateToAddIntake()},
                openIntakeList = {navController.navigateToIntakeList()}
            )
        }
        composable(route = NavigationRoutes.AddIntakeScreen.route) {
            AddWaterIntakeScreen(
                goBack = {navController.goBack()},
                save = vm::addIntake
            )
        }
        composable(route = NavigationRoutes.IntakeListScreen.route) {
            WaterIntakeListScreen(
                intakes = vm.intakes,
                padding = paddingValues,
                loadWaterIntake = vm::loadIntakes,
                navigateToAddIntake = {navController.navigateToAddIntake()}
            )
        }
    }
}

fun NavController.goBack() {
    this.popBackStack()
}

fun NavController.navigateToAddIntake() {
    this.navigate(NavigationRoutes.AddIntakeScreen.route)
}

fun NavController.navigateToIntakeList() {
    this.navigate(NavigationRoutes.IntakeListScreen.route)
}