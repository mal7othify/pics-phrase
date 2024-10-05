package org.company.picsphrase.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.company.picsphrase.items.FormKeys
import org.company.picsphrase.util.noRippleClickable
import org.jetbrains.compose.resources.stringResource

@Composable
fun PicsPhraseRadioGroup(
  options: List<FormKeys.FormOptionType>,
  selectedOption: FormKeys.FormOptionType,
  onClick: (FormKeys.FormOptionType) -> Unit
) {
  Column {
    options.forEach { option ->
      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =
        Modifier
          .noRippleClickable { onClick(option) }
          .fillMaxWidth()
      ) {
        RadioButton(
          selected = option == selectedOption,
          onClick = { onClick(option) },
          colors =
          RadioButtonDefaults.colors(
            selectedColor = Color(0xFFD81B60)
          )
        )
        Text(
          text = stringResource(option.displayName)
        )
      }
    }
  }
}
