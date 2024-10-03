package org.company.picsphrase

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import org.company.picsphrase.screens.MainScreen
import org.company.picsphrase.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun App() =
    AppTheme {
        MainScreen()
//        Scaffold(
//            topBar = {
//                TopAppBar(
//                    title = {
//                        Text("Pics Phrase")
//                    },
//                )
//            },
//        ) {
//            var selectedImage by remember { mutableStateOf<ImageBitmap?>(null) }
//            var openImagePicker by remember { mutableStateOf(value = false) }
//
//            // If image is empty
//            Column(
//                modifier =
//                    Modifier
//                        .verticalScroll(rememberScrollState())
//                        .padding(screenPadding),
//            ) {
//                if (selectedImage == null) {
//                    Box(
//                        modifier = Modifier.background(MaterialTheme.colorScheme.surfaceDim),
//                        contentAlignment = Alignment.Center,
//                    ) {
//                    }
//                } else {
//                    Image(
//                        modifier =
//                            Modifier
//                                .size(250.dp)
//                                .padding(16.dp),
//                        imageVector = vectorResource(Res.drawable.ic_cyclone), // TODO: Placeholder ...
//                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
//                        contentDescription = null,
//                    )
//                }
//
//                // TODO: Sanitize file name before uploading to Google Storage
//
//                // TODO: Limit the uploaded pictures to 3 images only.
//
//                // TODO: Allow only jpg -> png? to be uploded, otherwise show an error
//
//                // TODO: DO NOT PUSH THE BUCKET ID CODE and the Google Cloud Storage environment variables.
//
//                // TODO: Use Koin!!!
//            }
//        }
//
//        Column(
//            modifier =
//                Modifier
//                    .fillMaxSize()
//                    .windowInsetsPadding(WindowInsets.safeDrawing)
//                    .padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//        ) {
//            Text(
//                text = stringResource(Res.string.cyclone),
//                fontFamily = FontFamily(Font(Res.font.IndieFlower_Regular)),
//                style = MaterialTheme.typography.displayLarge,
//            )
//
//            var isRotating by remember { mutableStateOf(false) }
//
//            val rotate = remember { Animatable(0f) }
//            val target = 360f
//            if (isRotating) {
//                LaunchedEffect(Unit) {
//                    while (isActive) {
//                        val remaining = (target - rotate.value) / target
//                        rotate.animateTo(
//                            target,
//                            animationSpec =
//                                tween(
//                                    (1_000 * remaining).toInt(),
//                                    easing = LinearEasing,
//                                ),
//                        )
//                        rotate.snapTo(0f)
//                    }
//                }
//            }
//
//            ElevatedButton(
//                modifier =
//                    Modifier
//                        .padding(horizontal = 8.dp, vertical = 4.dp)
//                        .widthIn(min = 200.dp),
//                onClick = { isRotating = !isRotating },
//                content = {
//                    Icon(vectorResource(Res.drawable.ic_rotate_right), contentDescription = null)
//                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
//                    Text(
//                        stringResource(if (isRotating) Res.string.stop else Res.string.run),
//                    )
//                },
//            )
//
//            var isDark by LocalThemeIsDark.current
//            val icon =
//                remember(isDark) {
//                    if (isDark) {
//                        Res.drawable.ic_light_mode
//                    } else {
//                        Res.drawable.ic_dark_mode
//                    }
//                }
//
//            ElevatedButton(
//                modifier =
//                    Modifier
//                        .padding(horizontal = 8.dp, vertical = 4.dp)
//                        .widthIn(min = 200.dp),
//                onClick = { isDark = !isDark },
//                content = {
//                    Icon(vectorResource(icon), contentDescription = null)
//                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
//                    Text(stringResource(Res.string.theme))
//                },
//            )
//
//            val uriHandler = LocalUriHandler.current
//            TextButton(
//                modifier =
//                    Modifier
//                        .padding(horizontal = 8.dp, vertical = 4.dp)
//                        .widthIn(min = 200.dp),
//                onClick = { uriHandler.openUri("https://github.com/terrakok") },
//            ) {
//                Text(stringResource(Res.string.open_github))
//            }
//        }
    }
