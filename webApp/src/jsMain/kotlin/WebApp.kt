import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import com.phone.kmpsetup.App
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        Window("MusicApp-KMP") {
            Column(modifier = Modifier.fillMaxSize()) {
                App()
            }
        }
    }
}