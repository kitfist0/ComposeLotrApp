package dev.lotr.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.lotr.presentation.navigation.NavGraph
import dev.lotr.presentation.navigation.NavManager
import dev.lotr.presentation.theme.ComposeLotrAppTheme
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navManager: NavManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            ComposeLotrAppTheme {
                NavGraph(navController)
            }

            lifecycleScope.launchWhenStarted {
                navManager.routes.collect { route ->
                    route?.let { navController.navigate(it) }
                }
            }
        }
    }
}
