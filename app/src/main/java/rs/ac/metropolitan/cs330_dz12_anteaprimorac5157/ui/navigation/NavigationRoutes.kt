package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.ui.navigation

sealed class NavigationRoutes(val route: String) {
    object HomeScreen : NavigationRoutes(route = "home")
    object IntakeListScreen: NavigationRoutes(route = "intakeList")
    object AddIntakeScreen : NavigationRoutes(route = "addIntake")
}