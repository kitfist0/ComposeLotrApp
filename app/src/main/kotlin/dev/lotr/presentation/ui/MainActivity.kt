package dev.lotr.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import dev.lotr.presentation.NavGraph
import dev.lotr.presentation.theme.ComposeLotrAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLotrAppTheme {
                NavGraph()
            }
        }
    }
}
