package dev.lotr.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.lotr.presentation.ui.books.BooksScreen
import dev.lotr.presentation.ui.chapters.ChaptersScreen

@Composable
fun NavGraph(startDestination: NavDest = NavDest.BOOKS) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination.route,
    ) {
        composable(
            route = NavDest.BOOKS.route,
        ) {
            BooksScreen(viewModel = hiltViewModel())
        }
        composable(
            route = "${NavDest.CHAPTERS.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType }),
        ) {
            ChaptersScreen(it.arguments?.getString("id").orEmpty())
        }
    }
}
