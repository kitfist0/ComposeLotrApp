package dev.lotr.presentation.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import dev.lotr.R

@Composable
fun LoadingProgress(modifier: Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
    ) {
        Text(
            text = stringResource(R.string.loading),
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingProgressPreview() {
    LoadingProgress(Modifier)
}

@Composable
fun ColumnHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun ColumnHeaderPreview() {
    ColumnHeader("LOTR")
}

@Composable
fun ColumnSimpleItem(
    itemId: String,
    text: String,
    onItemClick: (String) -> Unit,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick.invoke(itemId) }
            .padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun ColumnSimpleItemPreview() {
    ColumnSimpleItem(
        itemId = "5cf58077b53e011a64671582",
        text = "The Fellowship Of The Ring",
        onItemClick = {},
    )
}
