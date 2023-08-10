package com.example.agendadecontatos.views

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agendadecontatos.views.layouts.BotaoPersonalizado
import com.example.agendadecontatos.views.layouts.OutlinedPersonalizado
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun salvarContatos(navController: NavController){

    var nome by remember{ mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Adicione um novo contato")
                },

                backgroundColor = Color(0xFF01A0A0),
                contentColor = Color.White,
            )
        },
    ) {
        Column(

        ) {
            //Textfield para o Nome (vem pré-pronto da classe layoutsProntos).
            OutlinedPersonalizado(
                value = nome,
                onValueChange = {nome = it},
                label = {
                        Text(text = "Nome")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .padding(top = 60.dp)
            )

            //Textfield para o Sobrenome
            OutlinedPersonalizado(
                value = sobrenome,
                onValueChange = {sobrenome = it},
                label = {
                    Text(text = "Sobrenome")
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
                    Text(text = "Idade")
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
                    Text(text = "Número")
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
                if (nome.isEmpty() && sobrenome.isEmpty() && idade.isEmpty() && numero.isEmpty()){
                    Toast.makeText(context,"Preencha todos os dados, PREGUIÇOSO(A)!",Toast.LENGTH_SHORT).show()
                }
                else if (nome.isEmpty()){
                    Toast.makeText(context,"Preencha o nome, seu jacaré.",Toast.LENGTH_SHORT).show()
                }
                   else if (sobrenome.isEmpty()){
                        Toast.makeText(context,"Preencha o Sobrenome, jovem marsupial!",Toast.LENGTH_SHORT).show()
                    }
                        else if (idade.isEmpty()){
                            Toast.makeText(context,"Preencha a idade, Bakayaro, Konoyaro!",Toast.LENGTH_SHORT).show()
                        }
                            else if (numero.isEmpty()){
                                Toast.makeText(context,"Coloca o número da gata lá",Toast.LENGTH_SHORT).show()
                            }
                                else{
                                    Toast.makeText(context,"Finalmente. Contato salvo com sucesso.",Toast.LENGTH_SHORT).show()
                                    navController.navigate("listaContatos")
                                }


            },
                nomeBotao = "Salvar",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 120.dp),

            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SalvarPreview(){
    salvarContatos(navController = rememberNavController())
}