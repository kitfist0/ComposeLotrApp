package dev.lotr.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.lotr.app.navigation.NavGraph
import dev.lotr.app.navigation.NavManager
import dev.lotr.app.theme.LotrAppTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navManager: NavManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val navController = rememberNavController()

            LotrAppTheme {
                Surface {
                    NavGraph(navController)
                }
            }

            LaunchedEffect(true) {
                navManager.routes.collect { navController.navigate(it) }
            }
        }
    }
}
