package com.example.appandroidnavigationcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import com.example.appandroidnavigationcompose.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appandroidnavigationcompose.Screen

@Composable
fun ProfileScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize().padding(all = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "Avatar do usuário",
                modifier = Modifier.size(72.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text("Universitário", fontWeight = FontWeight.Bold)
                Text("Online")
            }
        }

        Button(onClick = {
            navController.navigate(Screen.ProjectList.route)
        }) {
            Text("Listar Projetos")
        }
    }
}
