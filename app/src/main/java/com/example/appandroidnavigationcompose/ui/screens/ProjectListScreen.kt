package com.example.appandroidnavigationcompose.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
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

