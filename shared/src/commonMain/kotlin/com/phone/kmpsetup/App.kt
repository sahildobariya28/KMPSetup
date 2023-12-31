package com.phone.kmpsetup

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun App() {
    Column {
        val text = remember { mutableStateOf("Hello from Compose Multiplatform") }
        Text(text = text.value)
        Button(onClick = { text.value = "You clicked me!" }, content = {
            Text("Click Me ")
        })
    }
}