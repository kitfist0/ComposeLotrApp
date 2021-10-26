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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lotr.domain.model.Chapter

@Composable
fun ChaptersScreen(viewModel: ChaptersViewModel) {
    val chapters = viewModel.chapters.collectAsState()
    Body(
        chapters = chapters.value,
        onItemClick = { chapterId -> viewModel.onChapterClicked(chapterId) },
    )
}

@Composable
private fun Body(chapters: List<Chapter>, onItemClick: (chapterId: String) -> Unit) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyColumn {
            for (chapter in chapters) {
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onItemClick.invoke(chapter.id) }
                            .padding(16.dp),
                        text = chapter.name,
                    )
                }
            }
        }
    }
}
