package dev.lotr.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import dev.lotr.app.navigation.NavGraph
import dev.lotr.app.navigation.NavManager
import dev.lotr.app.theme.LotrAppTheme
import kotlinx.coroutines.flow.collect
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

            ProvideWindowInsets {
                LotrAppTheme {
                    Surface {
                        NavGraph(navController)
                    }
                }
            }

            lifecycleScope.launchWhenStarted {
                navManager.routes.collect { navController.navigate(it) }
            }
        }
    }
}
