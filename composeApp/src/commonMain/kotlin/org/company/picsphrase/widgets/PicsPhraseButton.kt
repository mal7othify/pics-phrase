package org.company.picsphrase.widgets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import org.company.picsphrase.theme.allRoundCornerShape8dp
import org.company.picsphrase.theme.iconSize
import org.company.picsphrase.theme.largePadding
import org.company.picsphrase.theme.mediumPadding

@Composable
fun PicsPhraseButton(
  title: String,
  leadingIcon: Painter,
  onClick: () -> Unit
) {
  Button(
    onClick = {
      onClick.invoke()
    },
    colors =
      ButtonDefaults.buttonColors().copy(
        containerColor = MaterialTheme.colorScheme.primary
      ),
    modifier =
      Modifier
        .fillMaxWidth()
        .padding(bottom = largePadding),
    shape = allRoundCornerShape8dp
  ) {
    Icon(
      painter = leadingIcon,
      contentDescription = title,
      modifier = Modifier.size(iconSize),
      tint = MaterialTheme.colorScheme.onPrimary
    )
    Spacer(modifier = Modifier.width(mediumPadding))
    Text(
      text = title,
      color = MaterialTheme.colorScheme.onPrimary
    )
  }
}
