import androidx.compose.ui.window.ComposeUIViewController
import org.company.picsphrase.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
