package org.company.picsphrase.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.company.picsphrase.theme.AppTheme
import org.company.picsphrase.util.noRippleClickable
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import picsphrase.composeapp.generated.resources.Res
import picsphrase.composeapp.generated.resources.ic_cyclone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var selectedImage by remember { mutableStateOf<ImageBitmap?>(null) }
    var selectedLanguage by remember { mutableStateOf("Arabic") }
    var includeHashtags by remember { mutableStateOf(true) }
    var captionStyle by remember { mutableStateOf("Long caption") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Pics Phrase",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp),
                    )
                },
            )
        },
        modifier =
            Modifier
                .fillMaxSize()
                .padding(30.dp),
    ) {
        Column(
            modifier =
                Modifier.background(
                    MaterialTheme.colorScheme.surface,
                ),
        ) {
            // TODO: Use animated visibility here to fade the box into the image
            if (selectedImage == null) {
                Box(
                    modifier =
                        Modifier
                            .size(300.dp, 200.dp)
                            .padding(bottom = 16.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(text = "No image uploaded yet ...", color = Color.Gray)
                    }
                }
            } else {
                // TODO: Add image
            }

            Button(
                onClick = { /* Upload action */ },
                colors = ButtonDefaults.buttonColors().copy(containerColor = Color(0xFFD81B60)),
                modifier = Modifier.padding(bottom = 16.dp),
                shape = RoundedCornerShape(8.dp),
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_cyclone),
                    contentDescription = "Upload Icon",
                    modifier = Modifier.size(20.dp),
                    tint = Color.White,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Upload Image", color = Color.White)
            }

            Text(
                text = "Choose Caption Language",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp),
            )

            RadioGroup(
                options = listOf("Arabic", "English"),
                selectedOption = selectedLanguage,
                onOptionSelected = { selectedLanguage = it },
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Include Hashtags?",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp),
            )

            RadioGroup(
                options = listOf("Yes", "No"),
                selectedOption = if (includeHashtags) "Yes" else "No",
                onOptionSelected = { includeHashtags = it == "Yes" },
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Caption Style:",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp),
            )

            RadioGroup(
                options = listOf("Long caption", "Short caption", "Surprise me!"),
                selectedOption = captionStyle,
                onOptionSelected = { captionStyle = it },
            )
        }
    }
}

@Composable
fun RadioGroup(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
) {
    Column {
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier =
                    Modifier
                        .noRippleClickable { onOptionSelected(option) }
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
            ) {
                RadioButton(
                    selected = option == selectedOption,
                    onClick = { onOptionSelected(option) },
                    colors =
                        RadioButtonDefaults.colors(
                            selectedColor = Color(0xFFD81B60),
                        ),
                )
                Text(text = option)
            }
        }
    }
}

@Preview()
@Composable
fun DefaultPreview() {
    AppTheme {
        MainScreen()
    }
}
