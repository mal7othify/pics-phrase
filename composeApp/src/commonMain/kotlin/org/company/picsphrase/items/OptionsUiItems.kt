package org.company.picsphrase.items

import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import picsphrase.composeapp.generated.resources.Res
import picsphrase.composeapp.generated.resources.app_name
import picsphrase.composeapp.generated.resources.first_option_arabic
import picsphrase.composeapp.generated.resources.first_option_english
import picsphrase.composeapp.generated.resources.second_option_no
import picsphrase.composeapp.generated.resources.second_option_yes
import picsphrase.composeapp.generated.resources.third_option_long
import picsphrase.composeapp.generated.resources.third_option_random
import picsphrase.composeapp.generated.resources.third_option_short

sealed class OptionsUiItems {
  data class TitleItem(
    val title: StringResource = Res.string.app_name
  ) : OptionsUiItems()

  @Serializable
  data class ImageItem(
    val imageBitmap: ImageBitmap? = null
  ) : OptionsUiItems()

  data class ButtonItem(
    val title: StringResource,
    val leadingIcon: DrawableResource,
    val buttonType: ButtonTypes
  ) : OptionsUiItems()

  data class OptionItem(
    val title: StringResource,
    val options: List<FormKeys.FormOptionType>
  ) : OptionsUiItems()

  @Serializable
  data class GeneratedCaptions(
    val captions: List<String>
  ) : OptionsUiItems()
}

enum class ButtonTypes {
  UPLOAD_PRIMARY,
  GENERATE,
  UPLOAD_SECONDARY
}

sealed class FormKeys {
  interface FormOptionType {
    val displayName: StringResource
  }

  enum class LanguageType(
    override val displayName: StringResource
  ) : FormOptionType {
    ARABIC(Res.string.first_option_arabic),
    ENGLISH(Res.string.first_option_english)
  }

  enum class IncludeHashtagsType(
    override val displayName: StringResource
  ) : FormOptionType {
    TRUE(Res.string.second_option_yes),
    FALSE(Res.string.second_option_no)
  }

  enum class CaptionType(
    override val displayName: StringResource
  ) : FormOptionType {
    LONG(Res.string.third_option_long),
    SHORT(Res.string.third_option_short),
    RANDOM(Res.string.third_option_random)
  }
}
