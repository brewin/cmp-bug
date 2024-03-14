import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.interop.UIKitView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.cinterop.ExperimentalForeignApi
import platform.MapKit.MKMapView
import platform.UIKit.UIColor
import platform.UIKit.UIView

actual fun getPlatformName(): String = "iOS"

@OptIn(ExperimentalForeignApi::class)
fun MainViewController() = ComposeUIViewController {
    Scaffold(
        topBar = { MyTopAppBar() },
        content = {
            UIKitView(
                factory = { MKMapView() },
                modifier = Modifier
                    .fillMaxSize()
                // Works with padding because UIView is no longer overlapping another UIView.
                //.padding(it)
            )
        }
    )
}

// It works when using a Box instead of a Scaffold.
@OptIn(ExperimentalForeignApi::class)
fun MainViewController2() = ComposeUIViewController {
    Box {
        UIKitView(
            factory = { MKMapView() },
            modifier = Modifier.fillMaxSize()
        )
        MyTopAppBar()
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalForeignApi::class)
@Composable
fun MyTopAppBar() {
    TopAppBar(
        title = { Text("TopAppBar") },
        actions = {
            UIKitView(
                factory = {
                    UIView().apply { backgroundColor = UIColor.redColor }
                },
                modifier = Modifier.size(48.dp)
            )
        },
        windowInsets = WindowInsets.systemBars
            .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black.copy(alpha = 0.5f)
        )
    )
}