package com.example.appandroidnavigationcompose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class Project(
    val id: Int,
    val name: String,
    val description: String,
    val language: String
)

val sampleProjects = listOf(
    Project(1, "App de Portfólio", "Este mesmo aplicativo que você está vendo.",
        "Kotlin"),
    Project(2, "API de E-commerce", "Backend para uma loja virtual.", "Java"),
    Project(3, "Website Institucional", "Frontend para uma empresa.", "JavaScript"),
    Project(4, "Análise de Dados", "Script para analisar dados de vendas.", "Python"),
    Project(5, "App de Lista de Tarefas", "Um simples app de To-Do.", "Kotlin")
)

@Composable
fun ProjectListScreen(navController: NavController){
    LazyColumn(){
        items(
            items = sampleProjects,
            key = {project -> project.id}
        ){ project -> ProjectCard(project = project, navController = navController) }
    }
}

@Composable
fun ProjectCard(project: Project, navController: NavController){
    Card(modifier = Modifier.clickable{
        navController.navigate("project_detail/${project.id}")
    }) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = project.name, fontWeight = FontWeight.Bold)
            Text(text = project.description, color = Color.Gray)
        }
    }
}

