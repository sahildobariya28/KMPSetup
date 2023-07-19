package com.phone.kmpsetup

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import io.github.xxfast.decompose.LocalComponentContext
import platform.UIKit.UIViewController

fun Application(): UIViewController = ComposeUIViewController {

    val lifecycle = LifecycleRegistry()
    val rootComponentContext = DefaultComponentContext(lifecycle = lifecycle)

    CompositionLocalProvider(LocalComponentContext provides rootComponentContext) {
        MaterialTheme {
            App()
        }
    }
}