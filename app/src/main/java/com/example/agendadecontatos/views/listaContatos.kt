package com.example.agendadecontatos.views

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agendadecontatos.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.agendadecontatos.AppDatabase
import com.example.agendadecontatos.dao.ContatoDao
import com.example.agendadecontatos.model.Contato
import com.example.agendadecontatos.views.layouts.novoContato
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private  lateinit var contatoDao: ContatoDao

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun listaContatos(navController : NavController){

    val context = LocalContext.current
    val listaContatos: MutableList<Contato> = mutableListOf()
    val scope = rememberCoroutineScope()

    scope.launch(Dispatchers.IO){
        contatoDao = AppDatabase.getInstance(context).contatoDao()
        val contatos = contatoDao.listar()

        for (contato in contatos){
            listaContatos.add(contato)
        }
    }

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
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    navController.navigate("salvarContatos")
                },
                    backgroundColor = Color(0xFF01A0A0),

                    ) {
                    Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                        contentDescription = "a",
                        modifier = Modifier.size(30.dp)
                    )
                }
            },
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                carregarBackground(path = "https://raw.githubusercontent.com/jonatas1096/Agenda-de-Contatos/master/app/src/main/res/drawable/backgroundprincipal.jpg")
            }

            LazyColumn {
                itemsIndexed(listaContatos){position, item ->
                    novoContato(navController, position, listaContatos, context)
                }
            }

        }
    }


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun carregarBackground(path: String){
    GlideImage(model = path, contentDescription = "",
        contentScale = ContentScale.Crop,
    modifier = Modifier.fillMaxSize())
}

/*
@Preview(showBackground = true)
@Composable
fun ContatosPreview(){
    listaContatos(navController = rememberNavController())
}*/