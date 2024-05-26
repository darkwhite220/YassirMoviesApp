package com.darkwhite.yassirmoviesapp.ui.screen.movie_detail

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.darkwhite.yassirmoviesapp.R
import com.darkwhite.yassirmoviesapp.data.model.MovieDetail
import com.darkwhite.yassirmoviesapp.ui.component.BodySmallText
import com.darkwhite.yassirmoviesapp.ui.component.LoadingIndicator
import com.darkwhite.yassirmoviesapp.ui.component.MyAsyncImage
import com.darkwhite.yassirmoviesapp.ui.component.MyText
import com.darkwhite.yassirmoviesapp.ui.component.backdropAspectRation
import com.darkwhite.yassirmoviesapp.ui.component.buttonSize
import com.darkwhite.yassirmoviesapp.ui.component.largeDp
import com.darkwhite.yassirmoviesapp.ui.component.mediumDp
import com.darkwhite.yassirmoviesapp.ui.component.smallDp
import com.darkwhite.yassirmoviesapp.ui.theme.AppIcon
import com.darkwhite.yassirmoviesapp.ui.theme.YassirMoviesAppTheme
import com.darkwhite.yassirmoviesapp.utils.Constants.EMPTY_STRING
import com.darkwhite.yassirmoviesapp.utils.formatMinutesToHoursAndMinutes

@Composable
fun MovieDetailScreen(
  onBackClick: () -> Unit = {},
  viewModel: MovieDetailViewModel = hiltViewModel()
) {
  val uiState = viewModel.uiState
  
  when {
    uiState.isLoading -> {
      LoadingIndicator(modifier = Modifier.fillMaxHeight())
    }
    uiState.errorMessage.isNotEmpty() -> {
      MovieDetailFailedRequest(errorMessage = uiState.errorMessage)
    }
    else -> {
      MovieDetailContent(
        movieDetail = uiState.movieDetail!!,
        onBackClick = onBackClick
      )
    }
  }
}


@Composable
private fun MovieDetailContent(
  movieDetail: MovieDetail,
  onBackClick: () -> Unit
) {
  Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
    
    MovieDetailBackdropUi(
      backdropPathUrl = movieDetail.backdropPathUrl,
      onBackClick = onBackClick
    )
    
    CategoryAndDurationRow(genresString = movieDetail.genresString, runtime = movieDetail.runtime)
    
    PlayAndDownloadUi()
    
    TitleAndDescription(title = movieDetail.title, overview = movieDetail.overview)
  }
}

@Composable
private fun CategoryAndDurationRow(
  genresString: String,
  runtime: Int
) {
  Row(
    modifier = Modifier.padding(horizontal = largeDp, vertical = smallDp),
    horizontalArrangement = Arrangement.spacedBy(mediumDp)
  ) {
    BodySmallText(
      modifier = Modifier.weight(1f),
      text = genresString,
      maxLines = 1,
    )
    BodySmallText(
      modifier = Modifier,
      text = formatMinutesToHoursAndMinutes(runtime),
      maxLines = 1,
    )
  }
}

@Composable
private fun MovieDetailBackdropUi(
  backdropPathUrl: String = EMPTY_STRING,
  onBackClick: () -> Unit = {},
) {
  val gradientList = listOf(
    Color.Transparent,
    MaterialTheme.colorScheme.background
  )
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .aspectRatio(backdropAspectRation)
  ) {
    // Backdrop image
    MyAsyncImage(
      modifier = Modifier.fillMaxSize(),
      imageUrl = backdropPathUrl
    )
    // Back button
    MovieDetailBackButton(
      modifier = Modifier.align(Alignment.TopStart),
      onBackClick = onBackClick
    )
    // Bottom backdrop image gradiant
    Box(
      modifier = Modifier
        .height(50.dp)
        .fillMaxWidth()
        .align(Alignment.BottomStart)
        .background(Brush.verticalGradient(colors = gradientList))
    )
  }
}

@Composable
private fun MovieDetailBackButton(
  modifier: Modifier = Modifier,
  onBackClick: () -> Unit = {}
) {
  val gradientList = listOf(
    Color.Transparent,
    MaterialTheme.colorScheme.background
  )
  Box(
    modifier = modifier
      .padding(mediumDp)
      .size(buttonSize)
      .clip(CircleShape)
      .clickable { onBackClick() }
      .background(brush = Brush.verticalGradient(colors = gradientList)),
  ) {
    Icon(
      modifier = Modifier.align(Alignment.Center),
      painter = painterResource(id = AppIcon.BackArrowIcon),
      contentDescription = stringResource(R.string.back)
    )
  }
}

@Composable
private fun PlayAndDownloadUi() {
  Spacer(modifier = Modifier.height(mediumDp))
  // Play button
  MovieDetailButton(
    iconId = AppIcon.PlayArrowIcon,
    textId = R.string.play,
    onClick = { /*TODO*/ }
  )
  Spacer(modifier = Modifier.height(mediumDp))
  // Download button
  MovieDetailButton(
    iconId = AppIcon.DownloadIcon,
    textId = R.string.download,
    onClick = { /*TODO*/ },
  )
  Spacer(modifier = Modifier.height(mediumDp))
}

@Composable
private fun MovieDetailButton(
  @DrawableRes iconId: Int,
  @StringRes textId: Int,
  onClick: () -> Unit,
) {
  Button(
    modifier = Modifier
      .padding(horizontal = largeDp)
      .fillMaxWidth(),
    shape = RoundedCornerShape(mediumDp),
    onClick = onClick,
  ) {
    Icon(
      painter = painterResource(id = iconId),
      contentDescription = stringResource(textId)
    )
    Spacer(modifier = Modifier.width(mediumDp))
    Text(text = stringResource(textId))
  }
}

@Composable
private fun TitleAndDescription(title: String, overview: String) {
  MyText(
    modifier = Modifier.padding(horizontal = largeDp),
    text = title,
    style = MaterialTheme.typography.bodyLarge
  )
  Spacer(modifier = Modifier.height(mediumDp))
  MyText(
    modifier = Modifier.padding(horizontal = largeDp),
    text = overview
  )
}

@Composable
private fun MovieDetailFailedRequest(
  modifier: Modifier = Modifier,
  errorMessage: String = "Failed request"
) {
  Box(
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    Text(
      modifier = modifier
        .fillMaxWidth()
        .padding(largeDp),
      text = errorMessage,
      textAlign = TextAlign.Center
    )
  }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MovieDetailBackdropUiPreview(modifier: Modifier = Modifier) {
  YassirMoviesAppTheme {
    Surface {
      MovieDetailBackdropUi()
    }
  }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CategoryAndDurationRowPreview(modifier: Modifier = Modifier) {
  YassirMoviesAppTheme {
    Surface {
      CategoryAndDurationRow(genresString = "Action • Horror", runtime = 150)
    }
  }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TitleAndDescriptionPreview(modifier: Modifier = Modifier) {
  YassirMoviesAppTheme {
    Surface {
      Column {
        TitleAndDescription(
          title = "Lorem Ipsum",
          overview = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis eleifend " +
              "consequat dolor, ac egestas arcu sollicitudin in. Phasellus ultrices nisl in libero " +
              "accumsan, ac hendrerit lectus imperdiet. Cras imperdiet tellus neque, sit amet" +
              " laoreet odio tempus vitae."
        )
      }
    }
  }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PlayAndDownloadUiPreview(modifier: Modifier = Modifier) {
  YassirMoviesAppTheme {
    Surface {
      PlayAndDownloadUi()
    }
  }
}

@Preview
@Composable
private fun MovieDetailFailedRequestPreview(modifier: Modifier = Modifier) {
  YassirMoviesAppTheme {
    Surface {
      MovieDetailFailedRequest()
    }
  }
}
