package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.navigation

sealed class NavigationRoutes(val route: String) {
    object HomeScreen : NavigationRoutes(route = "home")
    object TransactionListScreen : NavigationRoutes(route = "transactionList")
    object AddTransactionScreen : NavigationRoutes(route = "addTransaction")
}