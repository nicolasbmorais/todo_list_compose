package com.nicolasmorais.todolistapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nicolasmorais.todolistapp.ui.view.home.CreateTaskPage
import com.nicolasmorais.todolistapp.ui.view.home.HomePage
import com.nicolasmorais.todolistapp.ui.view.login.LoginPage
import com.nicolasmorais.todolistapp.ui.view.onboarding.RegisterUser

// Defina as rotas (strings únicas para identificar suas telas)
object Destinations {
    const val LOGIN_ROUTE = "login-page"
    const val HOME_ROUTE = "home-page"
    const val CREATE_TASK_ROUTE = "create-task-page"
    const val REGISTER_USER_ROUTE = "register-user-page"
}

@Composable
fun MyAppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController, // Passa o NavController para o NavHost
        startDestination = Destinations.LOGIN_ROUTE // Define a tela inicial
    ) {
        composable(Destinations.LOGIN_ROUTE) {
            // Chamada da tela inicial
            LoginPage(navController = navController)
        }
        composable(Destinations.REGISTER_USER_ROUTE) {
            RegisterUser(navController = navController)
        }
        composable(Destinations.HOME_ROUTE) {
            HomePage(navController = navController)
        }
        composable(Destinations.CREATE_TASK_ROUTE) {
            CreateTaskPage(navController = navController)
        }
    }
}