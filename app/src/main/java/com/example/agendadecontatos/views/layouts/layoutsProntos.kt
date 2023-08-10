package com.example.agendadecontatos.views.layouts

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.agendadecontatos.views.salvarContatos

@Composable //esses parâmetros nessa função abaixo servem para usar direto quando eu chamar a função
fun OutlinedPersonalizado(value: String,
                   onValueChange: (String) -> Unit,
                   label: @Composable () -> Unit,
                   keyboardOptions: KeyboardOptions,
                   modifier: Modifier,
                   //quando eu chamar ela, os parâmetros vão ser chamados e eu mudo eles
                   //a ideia aqui é reduzir o máximo de linha de código possível, definindo algumas coisas apenas uma vez só (o que não é o caso desses parâmetros).
) {

    OutlinedTextField(value,
        onValueChange,
        label = label,
        keyboardOptions = keyboardOptions,

        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Color(0xFF01A0A0),
            unfocusedBorderColor = Color(0xFF01A0A0),
            focusedBorderColor = Color(0xFF016F6F),
            focusedLabelColor = Color(0xFF045A5A),
        ),
        modifier = modifier,
        maxLines = 1
    )
}

@Composable
fun BotaoPersonalizado(onClick: () -> Unit,nomeBotao:String, modifier: Modifier){ //mesma lógica aqui. Vou pré-criar o máximo de coisas que se repetem para poupar código
    Button(onClick = onClick,
            modifier = modifier,

        //a cor vai vim padronizada, é só chamar que vai vir desta forma:
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF01A0A0),
            contentColor = Color.White,
        )

    ) {
        Text(text = nomeBotao,
        fontSize = 20.sp)
    }
}

