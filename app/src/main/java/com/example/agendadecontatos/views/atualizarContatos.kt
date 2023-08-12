package com.example.agendadecontatos.views

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agendadecontatos.AppDatabase
import com.example.agendadecontatos.dao.ContatoDao
import com.example.agendadecontatos.views.layouts.BotaoPersonalizado
import com.example.agendadecontatos.views.layouts.OutlinedPersonalizado
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private lateinit var contatoDao: ContatoDao

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun atualizarContatos(navController: NavController, uid: String){

    var nome by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Atualize o Contato",
                fontSize = 25.sp)
                },
                backgroundColor = Color(0xFF01A0A0),
                contentColor = Color.White,
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {

        //Coloquei a imgem antes da column para ela ficar atrás sozinha e tudo sobrepor ela
        Box(){
            carregarBackground(path = "https://raw.githubusercontent.com/jonatas1096/Agenda-de-Contatos/master/app/src/main/res/drawable/background.jpeg")
        }
        //Column para deixar todos os elementos um abaixo do outro.
        Column() {
            OutlinedPersonalizado(
                value = nome,
                onValueChange = { nome = it },
                label = {
                    Text(text = "Nome",
                        color = Color.White,
                        fontWeight = FontWeight.Bold)

                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .padding(top = 70.dp)
            )

            //sobrenome
            OutlinedPersonalizado(
                value = sobrenome,
                onValueChange = {sobrenome = it},
                label = {
                    Text(text = "Sobrenome",
                        color = Color.White,
                        fontWeight = FontWeight.Bold)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .padding(top = 15.dp, bottom = 15.dp)
            )

            //Textfield para a Idade
            OutlinedPersonalizado(
                value = idade,
                onValueChange = {idade = it},
                label = {
                    Text(text = "Idade",
                        color = Color.White,
                        fontWeight = FontWeight.Bold)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .padding(bottom = 15.dp)
            )

            //Textfield para o número
            OutlinedPersonalizado(
                value = numero,
                onValueChange = {numero = it},
                label = {
                    Text(text = "Número",
                        color = Color.White,
                        fontWeight = FontWeight.Bold)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .padding(bottom = 15.dp)
            )


            //Botão Padronizado:
            BotaoPersonalizado(

                    onClick = { //primeiro uma validação para garantir que todos os dados estejam preenchidos:
                        println("Valor de uid: $uid")


                    scope.launch (Dispatchers.IO){

                        var mensagem = false

                        if (nome.isEmpty() || sobrenome.isEmpty() || idade.isEmpty() || numero.isEmpty()){
                            mensagem = false
                        }
                        else{
                            mensagem = true

                            val uid = uid.replace("{", "").replace("}", "").trim() //chatgpt me ajudou a corrigir um erro com o valor do uid. Tava quebrando porque ele vinha com um valor zuado.
                            contatoDao = AppDatabase.getInstance(context).contatoDao()
                            contatoDao.atualizar(uid.toInt(),nome,sobrenome,idade,numero)


                        }

                        scope.launch (Dispatchers.Main){
                            if (mensagem){
                                Toast.makeText(context,"Está atualizado, Antílope.", Toast.LENGTH_SHORT).show()
                                navController.navigate("listaContatos")
                            }
                            if (nome.isEmpty() && sobrenome.isEmpty() && idade.isEmpty() && numero.isEmpty()){
                                Toast.makeText(context,"Você não aprende?",Toast.LENGTH_SHORT).show()
                            }
                            else if (nome.isEmpty()){
                                Toast.makeText(context,"Vai saber quem é sem um nome?",Toast.LENGTH_SHORT).show()
                            }
                            else if (sobrenome.isEmpty()){
                                Toast.makeText(context,"O sobrenome, amigo. O SOBRENOME.",Toast.LENGTH_SHORT).show()
                            }
                            else if (idade.isEmpty()){
                                Toast.makeText(context,"Todo mundo tem idade, ajuda aí né?",Toast.LENGTH_SHORT).show()
                            }
                            else if (numero.isEmpty()){
                                Toast.makeText(context,"Voodu é pra jacu.",Toast.LENGTH_SHORT).show()
                            }
                        }
                    }




                },
                nomeBotao = "Salvar as alterações",


                )
        }
    }


}


@Preview(showBackground = true)
@Composable
private fun atualizarPreview(){
    atualizarContatos(rememberNavController(), uid = "")
}