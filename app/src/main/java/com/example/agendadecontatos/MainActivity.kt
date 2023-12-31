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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.agendadecontatos.ui.theme.AgendaDeContatosTheme
import com.example.agendadecontatos.views.atualizarContatos
import com.example.agendadecontatos.views.listaContatos
import com.example.agendadecontatos.views.salvarContatos

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
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "listaContatos"){
        composable("listaContatos"){
            listaContatos(navController)
        }

        composable("salvarContatos"){
            salvarContatos(navController)
        }

        composable("atualizarContatos/{uid}",
            arguments = listOf(navArgument("uid"){})
        ){
            atualizarContatos(navController, it.arguments?.getString("uid").toString())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Main()
}