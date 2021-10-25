package dev.lotr.presentation.ui.books

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
import dev.lotr.domain.model.Book

@Composable
fun BooksScreen(
    viewModel: BooksViewModel,
) {
    val books = viewModel.books.collectAsState()
    Body(
        books = books.value,
        onItemClick = { bookId -> viewModel.onBookClicked(bookId) },
    )
}

@Composable
private fun Body(books: List<Book>, onItemClick: (bookId: String) -> Unit) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyColumn {
            for (book in books) {
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onItemClick.invoke(book.id) }
                            .padding(16.dp),
                        text = book.name,
                    )
                }
            }
        }
    }
}
