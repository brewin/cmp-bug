import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import androidx.compose.ui.window.ComposeUIViewController
import cocoapods.MapLibre.MLNMapView
import kotlinx.cinterop.ExperimentalForeignApi

actual fun getPlatformName(): String = "iOS"

@OptIn(ExperimentalForeignApi::class)
fun MainViewController() = ComposeUIViewController {
    UIKitView(
        factory = { MLNMapView() },
        modifier = Modifier.fillMaxSize()
    )
}
