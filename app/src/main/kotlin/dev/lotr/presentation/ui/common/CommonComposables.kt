package dev.lotr.presentation.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun LoadingProgress(modifier: Modifier){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ColumnHeader(text: String){
    Text(
        text = text,
        color = MaterialTheme.colors.secondary,
        style = MaterialTheme.typography.h1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}
