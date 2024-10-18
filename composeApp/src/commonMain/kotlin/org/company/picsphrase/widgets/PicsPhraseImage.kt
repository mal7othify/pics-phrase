package org.company.picsphrase.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.company.picsphrase.theme.allRoundCornerShape8dp
import org.company.picsphrase.theme.imageHeight
import org.company.picsphrase.theme.largePadding
import org.jetbrains.compose.resources.stringResource
import picsphrase.composeapp.generated.resources.Res
import picsphrase.composeapp.generated.resources.empty_image

@Composable
fun PicsPhraseImage(
  selectedImage: ImageBitmap?,
  isLoading: Boolean,
  currentProgress: Float,
  onClick: () -> Unit
) {
  Box(
    modifier =
      Modifier
        .fillMaxWidth()
        .height(imageHeight)
        .padding(bottom = largePadding)
        .background(
          color = MaterialTheme.colorScheme.surfaceDim,
          shape = allRoundCornerShape8dp
        ).clickable {
          onClick.invoke()
        },
    contentAlignment = Alignment.Center
  ) {
    if (selectedImage == null) {
      Text(
        text = stringResource(Res.string.empty_image),
        color = MaterialTheme.colorScheme.inverseOnSurface,
        textAlign = TextAlign.Center
      )
    } else {
      if (isLoading) {
        LinearProgressIndicator(
          modifier =
            Modifier
              .fillMaxWidth()
              .padding(horizontal = 33.dp),
          progress = { currentProgress },
          color = MaterialTheme.colorScheme.primary,
          trackColor = MaterialTheme.colorScheme.surfaceDim,
          strokeCap = StrokeCap.Round
        )
      }
      Image(
        modifier =
          Modifier
            .fillMaxWidth()
            .clip(allRoundCornerShape8dp),
        bitmap = selectedImage,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        alpha = if (isLoading) 0.3f else 1f
      )
    }
  }
}
