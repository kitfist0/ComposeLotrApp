package dev.lotr.presentation.ui.chapters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import dev.lotr.R
import dev.lotr.domain.common.Result
import dev.lotr.domain.model.Chapter
import dev.lotr.presentation.ui.common.LoadingProgress
import dev.lotr.presentation.ui.common.ColumnHeader

@Composable
fun ChaptersScreen(
    viewModel: ChaptersViewModel,
    modifier: Modifier = Modifier,
) {
    when (val state = viewModel.chapters.collectAsState().value) {
        is Result.Loading -> LoadingProgress(modifier)
        is Result.Success -> Body(
            chapters = state.data,
            onChapterClick = { chapterId -> viewModel.onChapterClicked(chapterId) },
            modifier,
        )
        is Result.Error -> {}
    }
}

@Composable
private fun Body(
    chapters: List<Chapter>,
    onChapterClick: (String) -> Unit,
    modifier: Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = rememberInsetsPaddingValues(LocalWindowInsets.current.systemBars),
        modifier = modifier.fillMaxSize(),
    ) {
        item {
            ColumnHeader(stringResource(R.string.chapters))
        }
        items(chapters) { chapter ->
            Text(
                text = chapter.name,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onChapterClick.invoke(chapter.id) }
                    .padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChaptersScreenPreview() {
    val bookId = "5cf58077b53e011a64671583"
    Body(
        chapters = listOf(
            Chapter("6091b6d6d58360f988133ba1", "The Departure of Boromir", bookId),
            Chapter("6091b6d6d58360f988133ba2", "The Riders of Rohan", bookId),
            Chapter("6091b6d6d58360f988133ba3", "The Uruk-Hai", bookId),
            Chapter("6091b6d6d58360f988133ba4", "Treebeard", bookId),
            Chapter("6091b6d6d58360f988133ba5", "The White Rider", bookId),
        ),
        onChapterClick = {},
        modifier = Modifier,
    )
}
