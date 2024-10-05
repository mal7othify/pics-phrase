package org.company.picsphrase.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import org.company.picsphrase.theme.largePadding
import org.company.picsphrase.theme.topAppBarPadding
import org.company.picsphrase.theme.topBarTitleFontSize

@Composable
fun PicsPhraseTitle(title: String) {
  Text(
    text = title,
    fontSize = topBarTitleFontSize,
    fontWeight = FontWeight.Bold,
    modifier =
      Modifier
        .fillMaxWidth()
        .padding(
          top = topAppBarPadding,
          bottom = largePadding
        ),
    textAlign = TextAlign.Center
  )
}
