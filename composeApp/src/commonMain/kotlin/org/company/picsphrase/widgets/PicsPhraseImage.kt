package org.company.picsphrase.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import org.company.picsphrase.theme.allRoundCornerShape8dp
import org.company.picsphrase.theme.imageHeight
import org.company.picsphrase.theme.largePadding
import org.jetbrains.compose.resources.stringResource
import picsphrase.composeapp.generated.resources.Res
import picsphrase.composeapp.generated.resources.empty_image

@Composable
fun PicsPhraseImage(
  selectedImage: ImageBitmap?,
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
      Image(
        modifier =
          Modifier
            .fillMaxWidth()
            .clip(allRoundCornerShape8dp),
        bitmap = selectedImage,
        contentDescription = null,
        contentScale = ContentScale.Crop
      )
    }
  }
}