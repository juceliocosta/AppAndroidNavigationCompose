package com.example.appandroidnavigationcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.appandroidnavigationcompose.ui.screens.LoginScreen
import com.example.appandroidnavigationcompose.ui.screens.ProfileScreen
import com.example.appandroidnavigationcompose.ui.screens.ProjectDetailScreen
import com.example.appandroidnavigationcompose.ui.screens.ProjectListScreen

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Profile : Screen("profile")
    object ProjectList : Screen("project_list")
    object ProjectDetail : Screen("project_detail/{projectId}")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ){
        composable(route = Screen.Login.route) { LoginScreen(navController = navController) }
        composable(route = Screen.Profile.route) { ProfileScreen(navController = navController) }
        composable(route = Screen.ProjectList.route) { ProjectListScreen(navController = navController) }
        composable(
            route = Screen.ProjectDetail.route,
            arguments = listOf(navArgument("projectId") { type = NavType.IntType })
        ){ backStackEntry ->
            val projectId = backStackEntry.arguments?.getInt("projectId")
            if (projectId != null) {
                ProjectDetailScreen(projectId = projectId, navController = navController)
            }
        }
    }
}