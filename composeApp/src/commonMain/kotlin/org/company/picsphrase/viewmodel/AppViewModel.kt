package org.company.picsphrase.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.company.picsphrase.items.ButtonTypes
import org.company.picsphrase.items.FormKeys
import org.company.picsphrase.items.OptionsUiItems
import picsphrase.composeapp.generated.resources.Res
import picsphrase.composeapp.generated.resources.first_option_title
import picsphrase.composeapp.generated.resources.ic_upload
import picsphrase.composeapp.generated.resources.second_option_title
import picsphrase.composeapp.generated.resources.third_option_title
import picsphrase.composeapp.generated.resources.upload_image_button

class AppViewModel : ViewModel() {
  private val optionsUiItems: ArrayList<OptionsUiItems> = arrayListOf()
  val optionsUiFlow = MutableStateFlow(arrayListOf<OptionsUiItems>())

  var selectedImage = mutableStateOf<ImageBitmap?>(null)
  var selectedLanguage = mutableStateOf(FormKeys.LanguageType.ARABIC)
  var includeHashtags = mutableStateOf(FormKeys.IncludeHashtagsType.TRUE)
  var captionStyle = mutableStateOf(FormKeys.CaptionType.RANDOM)

  var isLoading = mutableStateOf(false)
  var currentProgress = mutableStateOf(0.8f)

  init {
    setupUi()
  }

  private fun setupUi() =
    viewModelScope.launch {
      optionsUiItems.clear()
      optionsUiItems.add(OptionsUiItems.TitleItem())
      optionsUiItems.add(OptionsUiItems.ImageItem())
      optionsUiItems.add(
        OptionsUiItems.ButtonItem(
          title = Res.string.upload_image_button,
          leadingIcon = Res.drawable.ic_upload,
          buttonType = ButtonTypes.UPLOAD_PRIMARY
        )
      )
      // 3 Options
      optionsUiItems.add(
        OptionsUiItems.OptionItem(
          title = Res.string.first_option_title,
          options = FormKeys.LanguageType.entries // Use enum values as options
        )
      )

      optionsUiItems.add(
        OptionsUiItems.OptionItem(
          title = Res.string.second_option_title,
          options = FormKeys.IncludeHashtagsType.entries
        )
      )

      optionsUiItems.add(
        OptionsUiItems.OptionItem(
          title = Res.string.third_option_title,
          options = FormKeys.CaptionType.entries
        )
      )
      optionsUiFlow.emit(optionsUiItems)
    }
}
