package org.company.picsphrase.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import org.company.picsphrase.viewmodel.AppViewModel
import org.company.picsphrase.widgets.PicsPhraseButton
import org.company.picsphrase.widgets.PicsPhraseImage
import org.company.picsphrase.widgets.PicsPhraseOptions
import org.company.picsphrase.widgets.PicsPhraseTitle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainScreen() {
  val viewModel = viewModel<AppViewModel>()
  val uiItems by viewModel.optionsUiFlow.collectAsStateWithLifecycle(arrayListOf())

  val selectedImage by remember { viewModel.selectedImage }
  val scope = rememberCoroutineScope()
  val context = LocalPlatformContext.current

  val pickerLauncher =
    rememberFilePickerLauncher(
      type =
        FilePickerFileType.Custom(
          listOf("image/png", "image/jpeg")
        ),
      selectionMode = FilePickerSelectionMode.Single,
      onResult = { files ->
        scope.launch {
          files.firstOrNull()?.let { file ->
            // Do something with the selected file
            // You can get the ByteArray of the file
            viewModel.selectedImage.value =
              file.readByteArray(context).toImageBitmap()
            viewModel.isLoading.value = true
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
        .padding(horizontal = 30.dp)
  ) {
    items(uiItems) { item ->
      when (item) {
        is OptionsUiItems.TitleItem -> {
          PicsPhraseTitle(
            title =
              stringResource(
                item.title
              )
          )
        }

        is OptionsUiItems.ImageItem -> {
          PicsPhraseImage(
            selectedImage = selectedImage,
            isLoading = viewModel.isLoading.value,
            currentProgress = viewModel.currentProgress.value
          ) {
            if (!viewModel.isLoading.value) {
              pickerLauncher.launch()
            }
          }
        }

        is OptionsUiItems.ButtonItem -> {
          PicsPhraseButton(
            title = stringResource(item.title),
            leadingIcon = painterResource(item.leadingIcon),
            isEnabled = !viewModel.isLoading.value
          ) {
            pickerLauncher.launch()
          }
        }

        is OptionsUiItems.OptionItem -> {
          val selectedOption =
            when (item.options) {
              FormKeys.LanguageType.entries -> viewModel.selectedLanguage.value
              FormKeys.IncludeHashtagsType.entries -> viewModel.includeHashtags.value
              FormKeys.CaptionType.entries -> viewModel.captionStyle.value
              else -> null
            }

          PicsPhraseOptions(
            optionTitle = stringResource(item.title),
            options = item.options,
            selectedOption = selectedOption
          ) { option ->
            when (option) {
              is FormKeys.LanguageType -> viewModel.selectedLanguage.value = option
              is FormKeys.IncludeHashtagsType -> viewModel.includeHashtags.value = option
              is FormKeys.CaptionType -> viewModel.captionStyle.value = option
            }
          }
        }

        else -> {}
      }
    }
  }
}
