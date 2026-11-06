package com.example.appandroidnavigationcompose.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ProjectCard(project: Project, navController: NavController){
    Card(
        modifier = Modifier.clickable{
            navController.navigate("project_detail/${project.id}")
        }.fillMaxSize().padding(all = 8.dp)) {

        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = project.name, fontWeight = FontWeight.Bold)
            Text(text = project.description, color = Color.Gray)
        }
    }
}