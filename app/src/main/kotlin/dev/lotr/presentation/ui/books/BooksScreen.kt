package dev.lotr.presentation.ui.books

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import dev.lotr.domain.model.Book

@Composable
fun BooksScreen(
    viewModel: BooksViewModel,
    modifier: Modifier = Modifier,
) {
    val books = viewModel.books.collectAsState()
    Body(
        books = books.value,
        onBookClick = { bookId -> viewModel.onBookClicked(bookId) },
        modifier,
    )
}

@Composable
private fun Body(
    books: List<Book>,
    onBookClick: (String) -> Unit,
    modifier: Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.statusBarsPadding(),
    ) {
        items(books) { book ->
            Text(
                text = book.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onBookClick.invoke(book.id) }
                    .padding(16.dp)
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
