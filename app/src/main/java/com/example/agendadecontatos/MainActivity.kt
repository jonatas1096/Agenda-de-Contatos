package com.example.agendadecontatos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.agendadecontatos.ui.theme.AgendaDeContatosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgendaDeContatosTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    Text(text = "a")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Main()
}