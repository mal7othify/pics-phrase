package org.company.picsphrase.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mohamedrejeb.calf.core.LocalPlatformContext
import com.mohamedrejeb.calf.io.readByteArray
import com.mohamedrejeb.calf.picker.FilePickerFileType
import com.mohamedrejeb.calf.picker.FilePickerSelectionMode
import com.mohamedrejeb.calf.picker.rememberFilePickerLauncher
import com.mohamedrejeb.calf.picker.toImageBitmap
import kotlinx.coroutines.launch
import org.company.picsphrase.items.FormKeys
import org.company.picsphrase.items.OptionsUiItems
import org.company.picsphrase.theme.AppTheme
import org.company.picsphrase.util.noRippleClickable
import org.company.picsphrase.viewmodel.AppViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import picsphrase.composeapp.generated.resources.Res
import picsphrase.composeapp.generated.resources.empty_image
import picsphrase.composeapp.generated.resources.ic_upload

@Composable
fun MainScreen() {
  val viewModel = viewModel<AppViewModel>()
  val uiItems by viewModel.optionsUiFlow.collectAsState(arrayListOf())

  val selectedImage by remember { viewModel.selectedImage }
  val scope = rememberCoroutineScope()
  val context = LocalPlatformContext.current

  val pickerLauncher = rememberFilePickerLauncher(
    type = FilePickerFileType.Custom(
      listOf("image/png", "image/jpeg")
    ),
    selectionMode = FilePickerSelectionMode.Single,
    onResult = { files ->
      scope.launch {
        files.firstOrNull()?.let { file ->
          // Do something with the selected file
          // You can get the ByteArray of the file
          viewModel.selectedImage.value = file.readByteArray(context).toImageBitmap()
        }
      }
    }
  )

  LazyColumn(
    modifier =
    Modifier
      .background(
        MaterialTheme.colorScheme.surface
      ).fillMaxSize()
      .padding(30.dp)
  ) {
    items(uiItems) { item ->
      when (item) {
        is OptionsUiItems.TitleItem -> {
          Text(
            text = stringResource(item.title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier =
            Modifier
              .fillMaxWidth()
              .padding(vertical = 16.dp),
            textAlign = TextAlign.Center
          )
        }

        is OptionsUiItems.ImageItem -> {
          PicsPhraseImage(
            selectedImage = selectedImage
          ) {
            pickerLauncher.launch()
          }
        }

        is OptionsUiItems.ButtonItem -> {
          Button(
            onClick = { /* Upload action */ },
            colors =
            ButtonDefaults.buttonColors().copy(
              containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier =
            Modifier
              .fillMaxWidth()
              .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp)
          ) {
            Icon(
              painter = painterResource(Res.drawable.ic_upload),
              contentDescription = stringResource(item.title),
              modifier = Modifier.size(20.dp),
              tint = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = stringResource(item.title),
              color = MaterialTheme.colorScheme.onPrimary
            )
          }
        }

        is OptionsUiItems.OptionItem -> {
          Text(
            text = stringResource(item.title),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
          )

          val selectedOption =
            when (item.options) {
              FormKeys.LanguageType.entries -> viewModel.selectedLanguage.value
              FormKeys.IncludeHashtagsType.entries -> viewModel.includeHashtags.value
              FormKeys.CaptionType.entries -> viewModel.captionStyle.value
              else -> null
            }

          selectedOption?.let {
            RadioGroup(
              options = item.options,
              selectedOption = it,
              onClick = { option ->
                when (option) {
                  is FormKeys.LanguageType -> viewModel.selectedLanguage.value = option
                  is FormKeys.IncludeHashtagsType -> viewModel.includeHashtags.value = option
                  is FormKeys.CaptionType -> viewModel.captionStyle.value = option
                }
              }
            )
          }
          Spacer(modifier = Modifier.height(12.dp))
        }

        else -> {}
      }
    }
  }
}

@Composable
fun RadioGroup(
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

@Preview()
@Composable
private fun DefaultPreview() {
  AppTheme {
    MainScreen()
  }
}
