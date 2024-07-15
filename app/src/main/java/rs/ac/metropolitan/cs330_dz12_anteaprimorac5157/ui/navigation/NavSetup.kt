package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.AppViewModel
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.page.AddTransactionScreen
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.page.TransactionDetailScreen
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
                navigateToTransactionDetails = { transactionId ->
                    navController.navigate(NavigationRoutes.TransactionDetailsScreen.createRoute(transactionId))
                }
            )
        }
        composable(
            route = NavigationRoutes.AddTransactionScreen.route,
            arguments = listOf(navArgument("transactionId") { type = NavType.IntType; defaultValue = -1 })
        ) { backStackEntry ->
            val transactionId = backStackEntry.arguments?.getInt("transactionId") ?: -1
            val transaction = if (transactionId != -1) vm.transactions.value?.find { it.id == transactionId } else null
            AddTransactionScreen(
                transaction = transaction,
                goBack = { navController.popBackStack() },
                save = { newTransaction ->
                    if (transaction != null) {
                        vm.updateTransaction(newTransaction)
                    } else {
                        vm.addTransaction(newTransaction)
                    }
                    // Forsiraj osvjezavanje za TransactionListScreen
                    navController.popBackStack()
                    navController.navigate(NavigationRoutes.TransactionListScreen.route) {
                        popUpTo(NavigationRoutes.TransactionListScreen.route) { inclusive = true }
                    }
                }
            )
        }
        composable(
            route = NavigationRoutes.TransactionDetailsScreen.route,
            arguments = listOf(navArgument("transactionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val transactionId = backStackEntry.arguments?.getInt("transactionId") ?: -1
            val transaction = vm.transactions.value?.find { it.id == transactionId }
            TransactionDetailScreen(
                transaction = transaction,
                onDelete = { deletedTransaction ->
                    vm.deleteTransaction(deletedTransaction)
                    navController.popBackStack()
                },
                onEdit = { editedTransaction ->
                    navController.navigate(NavigationRoutes.AddTransactionScreen.createRoute(editedTransaction.id))
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

fun NavController.goBack() {
    this.popBackStack()
}

fun NavController.navigateToAddTransaction() {
    this.navigate(NavigationRoutes.AddTransactionScreen.createRoute())
}

fun NavController.navigateToTransactionList() {
    this.navigate(NavigationRoutes.TransactionListScreen.route)
}