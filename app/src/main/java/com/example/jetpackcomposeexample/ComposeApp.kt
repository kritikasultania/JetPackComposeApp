package com.example.jetpackcomposeexample

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposeexample.Destination.DashBoard
import com.example.jetpackcomposeexample.Destination.Login
import com.example.jetpackcomposeexample.Destination.ViewAll

@Composable
fun ComposeApp() {
    val navController = rememberNavController()

    val actions = remember(navController) { Actions(navController) }
    MaterialTheme {
        NavHost(navController = navController, startDestination = Login) {

            composable(Login) {
                LoginScreen(actions.openDashboard)
            }

            composable(DashBoard) {
                HomeScreen(actions.openViewAll)
            }

            composable(ViewAll) {
                ViewAllScreen()
            }
        }
    }
}

/**
 * Models the screens in the app and any arguments they require.
 */
object Destination {
    const val Login = "Login"
    const val DashBoard = "DashBoard"
    const val ViewAll = "ViewAll"
}

/**
 * Models the navigation actions in the app.
 */
class Actions(navController: NavHostController) {

    val openDashboard: () -> Unit = {
        navController.navigate(DashBoard)
    }

    val openViewAll: (FlowersData) -> Unit = {
        navController.navigate(ViewAll)
    }
}