package dev.lotr.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.lotr.presentation.ui.chapter.ChapterScreen
import dev.lotr.presentation.ui.chapters.ChaptersScreen

@Composable
fun NavGraph(startDestination: NavDest = NavDest.CHAPTERS) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination.route,
    ) {
        composable(
            route = NavDest.CHAPTERS.route,
        ) {
            ChaptersScreen(
                onChapterClick = { id ->
                    navController.navigate("${NavDest.CHAPTER.route}/${id}")
                }
            )
        }
        composable(
            route = "${NavDest.CHAPTER.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType }),
        ) {
            ChapterScreen(it.arguments?.getInt("id") ?: -1)
        }
    }
}
