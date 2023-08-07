package com.example.agendadecontatos.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agendadecontatos.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun listaContatos(navController : NavController){

    Column(

    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Contatos",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                        )
                    },
                    backgroundColor = Color(0xFF01A0A0),
                    contentColor = Color.White,
                )
            }
        ) {
            ConstraintLayout() {
                val (novocontatobotao)= createRefs()

            }
            FloatingActionButton(onClick = {

            }, //to tentando adicionar um modifier aqui p usar parent nele
                backgroundColor = Color(0xFF01A0A0),

                ) {
                Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_novocontato),
                    contentDescription = "a",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ContatosPreview(){
    listaContatos(navController = rememberNavController())
}