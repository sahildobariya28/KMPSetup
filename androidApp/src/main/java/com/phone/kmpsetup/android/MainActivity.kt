package com.phone.kmpsetup.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.defaultComponentContext
import com.phone.kmpsetup.App
import io.github.xxfast.decompose.LocalComponentContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WindowCompat.setDecorFitsSystemWindows(window, true)
            val rootComponentContext: DefaultComponentContext = defaultComponentContext()

            CompositionLocalProvider(
                LocalComponentContext provides rootComponentContext,
            ) {
                App()
            }
        }
    }
}
