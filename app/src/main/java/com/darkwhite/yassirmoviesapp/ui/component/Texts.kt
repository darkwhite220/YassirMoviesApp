package com.darkwhite.yassirmoviesapp.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow


@Composable
fun BodySmallText(
  modifier: Modifier = Modifier,
  text: String,
  maxLines: Int = Int.MAX_VALUE,
) {
  MyText(
    modifier = modifier,
    text = text,
    style = MaterialTheme.typography.bodySmall,
    maxLines = maxLines
  )
}

@Composable
fun MyText(
  modifier: Modifier = Modifier,
  text: String,
  style: TextStyle = MaterialTheme.typography.bodyMedium,
  maxLines: Int = Int.MAX_VALUE,
) {
  Text(
    modifier = modifier,
    text = text,
    style = style,
    maxLines = maxLines,
    overflow = TextOverflow.Ellipsis
  )
}
