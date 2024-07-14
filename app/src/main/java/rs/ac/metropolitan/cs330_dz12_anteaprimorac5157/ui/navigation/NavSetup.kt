package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.AppViewModel
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.page.AddTransactionScreen
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.page.TransactionListScreen

@Composable
fun NavSetup(vm: AppViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val paddingValues = PaddingValues()

    NavHost(navController = navController, startDestination = NavigationRoutes.TransactionListScreen.route) {
        composable(route = NavigationRoutes.TransactionListScreen.route) {
            TransactionListScreen(
                transactions = vm.transactions,
                padding = paddingValues,
                loadTransactions = vm::loadTransactions,
                navigateToAddTransaction = { navController.navigateToAddTransaction() },
                //navigateToTransactionDetails = { /* TODO: Implement this */ }
            )
        }
        composable(route = NavigationRoutes.AddTransactionScreen.route) {
            AddTransactionScreen(
                goBack = { navController.goBack() },
                save = vm::addTransaction
            )
        }
    }
}

fun NavController.goBack() {
    this.popBackStack()
}

fun NavController.navigateToAddTransaction() {
    this.navigate(NavigationRoutes.AddTransactionScreen.route)
}

fun NavController.navigateToTransactionList() {
    this.navigate(NavigationRoutes.TransactionListScreen.route)
}