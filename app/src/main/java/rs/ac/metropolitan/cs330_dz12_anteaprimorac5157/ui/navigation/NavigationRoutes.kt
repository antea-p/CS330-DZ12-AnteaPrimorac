package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.navigation

sealed class NavigationRoutes(val route: String) {
    object TransactionListScreen : NavigationRoutes(route = "transactionList")
    object AddTransactionScreen : NavigationRoutes(route = "addTransaction?transactionId={transactionId}") {
        fun createRoute(transactionId: Int? = null) =
            "addTransaction" + (transactionId?.let { "?transactionId=$it" } ?: "")
    }
    object TransactionDetailsScreen : NavigationRoutes(route = "transactionDetails/{transactionId}") {
        fun createRoute(transactionId: Int) = "transactionDetails/$transactionId"
    }
}