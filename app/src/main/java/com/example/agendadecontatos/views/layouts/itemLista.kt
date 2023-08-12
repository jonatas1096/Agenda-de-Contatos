package com.example.agendadecontatos.views.layouts

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agendadecontatos.AppDatabase
import com.example.agendadecontatos.R
import com.example.agendadecontatos.dao.ContatoDao
import com.example.agendadecontatos.model.Contato
import com.example.agendadecontatos.views.salvarContatos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private lateinit var  contatoDao: ContatoDao
@Composable
fun novoContato(
    navController: NavController,
    position: Int,
    listaContatos: MutableList<Contato>,
    context: Context
){

    val nome = listaContatos[position].nome
    val sobrenome = listaContatos[position].sobrenome
    val idade = listaContatos[position].idade
    val numero = listaContatos[position].numero
    val uid = listaContatos[position].usuarioID

    val contato = listaContatos[position]

    val scope = rememberCoroutineScope()

    //essa função serve para mostrar aquelas caixa de texto que o android tem. Vamos usar no método de deletar o usuário em si.
    fun alertaDeletar(){
        val alertaCaixa = AlertDialog.Builder(context) //construindo o alerta
        alertaCaixa.setTitle("Excluir contato?") //chamando o objeto e setando o titulo dele
        alertaCaixa.setMessage("Esta ação não poderá ser desfeita!") //setando a mensagem

        //o negativeButton referencia o "cancelar", a ação de cancelar a função em si.
        alertaCaixa.setNegativeButton("Cancelar"){_,_,-> //aqui é só fazer isso pra nao retornar nada

        }
        //o contrário do negativo.
        alertaCaixa.setPositiveButton("Confirmar"){_,_,->
            scope.launch (Dispatchers.IO){
                contatoDao = AppDatabase.getInstance(context).contatoDao()
                contatoDao.deletar(uid)
                listaContatos.remove(contato)
            }

            scope.launch(Dispatchers.Main){
                navController.navigate("listaContatos")
                Toast.makeText(context,"Contato excluído com sucesso.",Toast.LENGTH_SHORT).show()
            }

        }
        alertaCaixa.show() //mostrar a caixa
    }

    Card(
        backgroundColor = Color.White,
        contentColor = Color.Black,
        elevation = 8.dp,
        shape = RoundedCornerShape(30.dp),
        border = BorderStroke(5.dp, Color(0xFF01A0A0)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 20.dp, 20.dp, 5.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(20.dp)
        ) {
            //primeiro criamos as referencias para usar o parent:
            val (txtNome, txtIdade, txtNumero, iconAtualizar, iconDeletar) = createRefs()


            //agora começamos a construir elas:
            Text(text = "$nome $sobrenome",
            fontSize = 25.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(txtNome){
                top.linkTo(parent.top, margin = 5.dp)
                start.linkTo(parent.start, margin = 5.dp)
            })


            Text(text = "$idade anos",
            fontSize = 20.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(txtIdade){
                top.linkTo(txtNome.bottom, margin = 5.dp)
                start.linkTo(parent.start, margin = 5.dp)
            })

            Text(text = "Cel: ${numero.take(11)}",
                fontSize = 20.sp,
            modifier = Modifier.constrainAs(txtNumero){
                top.linkTo(txtIdade.bottom, margin = 5.dp)
                start.linkTo(parent.start, margin = 5.dp)
            })

            Button(onClick = {
                    navController.navigate("atualizarContatos/{$uid}")
            },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                ),
                elevation = ButtonDefaults.elevation(
                    disabledElevation = 0.dp,
                    defaultElevation = 0.dp
                ),
            modifier = Modifier.constrainAs(iconAtualizar){
                start.linkTo(parent.start, margin = 185.dp)
                top.linkTo(parent.top, margin = 58.dp)

            }) {
                Image(ImageVector.vectorResource(id = R.drawable.ic_editar), contentDescription = "",
                modifier = Modifier.size(34.dp))
            }

            Button(onClick = {
                alertaDeletar()
            },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                ),
                elevation = ButtonDefaults.elevation(
                    disabledElevation = 0.dp,
                    defaultElevation = 0.dp
                ),
                modifier = Modifier.constrainAs(iconDeletar){
                    start.linkTo(iconAtualizar.end)
                    top.linkTo(parent.top, margin = 58.dp)
                }) {
                Image(ImageVector.vectorResource(id = R.drawable.ic_deletar), contentDescription = "",
                    modifier = Modifier.size(34.dp))
            }
        }
    }



}




@Preview(showBackground = true)
@Composable
    fun itemPreview(){
    val navController = rememberNavController()
    val exampleContacts = mutableListOf(
        Contato("Nome Exemplo", "Sobrenome Exemplo", "25", "123456789101112"),
        // Adicione mais contatos de exemplo aqui...
    )
    val context = LocalContext.current // Este é um exemplo, considere apropriado para sua pré-visualização

    novoContato(navController, 0, exampleContacts, context)
}



