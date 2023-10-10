package dev.lotr.app.ui.chapters

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.lotr.app.R
import dev.lotr.app.ui.common.*
import dev.lotr.domain.common.Result
import dev.lotr.domain.model.Chapter
import kotlinx.coroutines.launch

@Composable
fun ChaptersScreen(
    viewModel: ChaptersViewModel,
    modifier: Modifier = Modifier,
) {
    when (val state = viewModel.stateFlow.collectAsState().value) {
        is Result.Loading -> LoadingProgress(modifier)
        is Result.Success -> Body(
            chapters = state.data,
            onChapterClick = { chapterId -> viewModel.onChapterClicked(chapterId) },
            modifier,
        )
        is Result.Error -> ErrorScreen(
            message = state.message,
            onRetryClicked = { viewModel.fetchData() },
            modifier,
        )
    }
}

@Composable
private fun Body(
    chapters: List<Chapter>,
    onChapterClick: (String) -> Unit,
    modifier: Modifier,
) {
    val listState = rememberLazyListState()
    val firstItemIndex = remember { mutableIntStateOf(listState.firstVisibleItemIndex) }
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = WindowInsets.statusBars.only(WindowInsetsSides.Top).asPaddingValues(),
        modifier = modifier.fillMaxSize(),
    ) {
        item {
            ColumnHeader(stringResource(R.string.chapters))
        }
        items(chapters) { chapter ->
            ColumnSimpleItem(
                itemId = chapter.id,
                text = chapter.name,
                onItemClick = onChapterClick,
            )
        }
        if (chapters.isNotEmpty() && listState.firstVisibleItemIndex != firstItemIndex.intValue) {
            item {
                ColumnFooterButton(text = stringResource(R.string.scroll_to_top)) {
                    coroutineScope.launch { listState.scrollToItem(0) }
                }
            }
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
