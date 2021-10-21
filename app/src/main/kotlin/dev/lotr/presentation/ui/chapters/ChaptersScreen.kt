package dev.lotr.presentation.ui.chapters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChaptersScreen(onChapterClick: (chapterId: Int) -> Unit) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyColumn {
            for (i in 1..62) {
                item {
                    ChapterItem(i) { onChapterClick.invoke(it) }
                }
            }
        }
    }
}

@Composable
private fun ChapterItem(chapterId: Int, onItemClick: (chapterId: Int) -> Unit) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick.invoke(chapterId) }
            .padding(16.dp),
        text = "Chapter #$chapterId",
    )
}
