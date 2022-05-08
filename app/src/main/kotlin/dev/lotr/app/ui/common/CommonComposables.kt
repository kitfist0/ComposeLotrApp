package dev.lotr.app.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import dev.lotr.app.R

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
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingProgressPreview() {
    LoadingProgress(Modifier)
}

@Composable
fun ErrorScreen(
    message: String,
    onRetryClicked: () -> Unit,
    modifier: Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
    ) {
        Image(
            painterResource(R.mipmap.ic_mordor),
            contentDescription = null,
            modifier = Modifier
                .alpha(0.8f)
                .padding(2.dp),
        )
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    top = 8.dp,
                    end = 16.dp,
                    bottom = 16.dp,
                ),
        )
        OutlinedButton(
            onClick = { onRetryClicked.invoke() },
            modifier = Modifier.padding(8.dp),
        ) {
            Text(
                text = stringResource(R.string.retry),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorScreenPreview() {
    ErrorScreen(
        message = "Something went wrong :/",
        onRetryClicked = {},
        Modifier,
    )
}

@Composable
fun ColumnFooterButton(
    text: String,
    onClicked: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.CenterEnd,
        modifier = Modifier.fillMaxWidth(),
    ) {
        ExtendedFloatingActionButton(
            text = { Text(text) },
            icon = { Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null) },
            onClick = { onClicked.invoke() },
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(16.dp)
                .height(36.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ColumnFooterButtonPreview() {
    ColumnFooterButton("Scroll up") {}
}

@Composable
fun ColumnHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
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
            .padding(16.dp),
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
