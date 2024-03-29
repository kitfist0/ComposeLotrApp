package dev.lotr.app.ui.books

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.lotr.app.R
import dev.lotr.app.ui.common.ColumnHeader
import dev.lotr.app.ui.common.ColumnSimpleItem
import dev.lotr.app.ui.common.ErrorScreen
import dev.lotr.app.ui.common.LoadingProgress
import dev.lotr.domain.common.Result
import dev.lotr.domain.model.Book

@Composable
fun BooksScreen(
    viewModel: BooksViewModel,
    modifier: Modifier = Modifier,
) {
    when (val state = viewModel.stateFlow.collectAsState().value) {
        is Result.Loading -> LoadingProgress(modifier)
        is Result.Success -> Body(
            books = state.data,
            onBookClick = { bookId -> viewModel.onBookClicked(bookId) },
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
    books: List<Book>,
    onBookClick: (String) -> Unit,
    modifier: Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
    ) {
        item {
            ColumnHeader(stringResource(R.string.app_name))
        }
        items(books) { book ->
            ColumnSimpleItem(
                itemId = book.id,
                text = book.name,
                onItemClick = onBookClick,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BooksScreenPreview() {
    Body(
        books = listOf(
            Book("5cf58077b53e011a64671582", "The Fellowship Of The Ring"),
            Book("5cf58077b53e011a64671583", "The Two Towers"),
            Book("5cf58077b53e011a64671584", "The Return Of The King"),
        ),
        onBookClick = {},
        modifier = Modifier,
    )
}
