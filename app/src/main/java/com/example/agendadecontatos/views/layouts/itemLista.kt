package com.example.agendadecontatos.views.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.compose.rememberNavController
import com.example.agendadecontatos.R
import com.example.agendadecontatos.views.salvarContatos

class itemLista {

@Composable
fun novoContato(){

    Card(
        backgroundColor = Color.White,
        contentColor = Color.Black,
        elevation = 10.dp,
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 20.dp, 20.dp, 35.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(20.dp)
        ) {
            //primeiro criamos as referencias para usar o parent:
            val (txtNome, txtSobrenome, txtIdade, txtNumero, iconAtualizar, iconDeletar) = createRefs()


            //agora começamos a construir elas:
            Text(text = "Jonatas Bahia",
            fontSize = 20.sp,
            modifier = Modifier.constrainAs(txtNome){
                top.linkTo(parent.top, margin = 5.dp)
                start.linkTo(parent.start, margin = 5.dp)
            })


            Text(text = "327",
            fontSize = 18.sp,
            modifier = Modifier.constrainAs(txtIdade){
                top.linkTo(txtNome.bottom, margin = 5.dp)
                start.linkTo(parent.start, margin = 5.dp)
            })

            Text(text = "999999999999",
            modifier = Modifier.constrainAs(txtNumero){
                top.linkTo(txtIdade.bottom, margin = 5.dp)
                start.linkTo(parent.start, margin = 5.dp)
            })

            Button(onClick = {

            },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                ),
                elevation = ButtonDefaults.elevation(
                    disabledElevation = 0.dp,
                    defaultElevation = 0.dp
                ),
            modifier = Modifier.constrainAs(iconAtualizar){
                start.linkTo(parent.start, margin = 170.dp)
            }) {
                Image(ImageVector.vectorResource(id = R.drawable.ic_editar), contentDescription = "")
            }
        }
    }



}




@Preview(showBackground = true)
@Composable
    fun itemPreview(){
     novoContato()
    }
}


