package com.example.appandroidnavigationcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appandroidnavigationcompose.R
import com.example.appandroidnavigationcompose.Screen

@Composable
fun TextField(
    label: String,
    text: String,
    onTextChange: (String) -> Unit
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth()
    )
}
@Composable
fun StatelessEntry(
    user: String,
    pass: String,
    onUserChange: (String) -> Unit,
    onPassChange: (String) -> Unit,
    onEntryClick: () -> Unit
){
    Image(
        painter = painterResource(id = R.drawable.project),
        contentDescription = "Avatar do usuário",
        modifier = Modifier.size(226.dp) //Tamanho da imagem
    )
    Text(
        text = "Bem-vindo!",
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
    )
    TextField(label = "Usuário", text = user, onTextChange = onUserChange)
    TextField(label = "Senha", text = pass, onTextChange = onPassChange)
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onEntryClick() },
        enabled= user.isNotEmpty() && pass.isNotEmpty()
    ){
        Text("Entrar")
    }
}

@Composable
fun LoginScreen(
    navController: NavController
) {
    var usuario by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize().padding(all = 28.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        StatelessEntry(
            usuario,
            senha,
            onUserChange = {newUser -> usuario = newUser},
            onPassChange = {newPass -> senha = newPass},
            onEntryClick = {
                navController.navigate(Screen.Profile.route)
            }
        )
    }
}