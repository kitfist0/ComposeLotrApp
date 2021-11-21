package dev.lotr.app.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.lotr.app.ui.books.BooksScreen
import dev.lotr.app.ui.chapters.ChaptersScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavDest.BOOKS.toRoute(),
    ) {
        composable(NavDest.BOOKS.toRoute()) {
            BooksScreen(hiltViewModel())
        }
        composable(NavDest.CHAPTERS.toRouteWithParamName(NavParams.BOOK_ID)) {
            ChaptersScreen(hiltViewModel())
        }
    }
}
