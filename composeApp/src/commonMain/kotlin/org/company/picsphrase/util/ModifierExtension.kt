package org.company.picsphrase.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

/**
 * Adds a clickable modifier to a composable without the ripple effect.
 *
 * @param onClick Callback invoked when the composable is clicked.
 *
 * Usage:
 * ```
 * Box(
 *     modifier = Modifier
 *         .noRippleClickable { /* Handle click */ }
 *         .background(Color.Gray)
 * )
 * ```
 */
@Composable
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier =
  this.clickable(
    indication = null,
    interactionSource = remember { MutableInteractionSource() },
    onClick = onClick
  )
