package org.company.picsphrase.widgets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import org.company.picsphrase.items.FormKeys
import org.company.picsphrase.theme.mainTitleFontSize
import org.company.picsphrase.theme.mediumPadding

@Composable
fun PicsPhraseOptions(
  optionTitle: String,
  options: List<FormKeys.FormOptionType>,
  selectedOption: FormKeys.FormOptionType?,
  onClick: (FormKeys.FormOptionType) -> Unit
) {
  Text(
    text = optionTitle,
    fontSize = mainTitleFontSize,
    fontWeight = FontWeight.SemiBold
  )

  selectedOption?.let {
    PicsPhraseRadioGroup(
      options = options,
      selectedOption = it,
      onClick = { option ->
        onClick.invoke(option)
      }
    )
  }
  Spacer(modifier = Modifier.height(mediumPadding))
}
