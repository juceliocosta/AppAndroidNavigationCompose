package com.example.appandroidnavigationcompose.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appandroidnavigationcompose.database.AppDatabase

//data class Project(
//    val id: Int,
//    val name: String,
//    val description: String,
//    val language: String
//)

//val sampleProjects = listOf(
//    Project(1, "App de Portfólio", "Este mesmo aplicativo que você está vendo.",
//        "Kotlin"),
//    Project(2, "API de E-commerce", "Backend para uma loja virtual.", "Java"),
//    Project(3, "Website Institucional", "Frontend para uma empresa.", "JavaScript"),
//    Project(4, "Análise de Dados", "Script para analisar dados de vendas.", "Python"),
//    Project(5, "App de Lista de Tarefas", "Um simples app de To-Do.", "Kotlin")
//)

@Composable
fun ProjectListScreen(
    navController: NavController,
//    viewModel: ProjectViewModel = viewModel()
){
    val context = LocalContext.current
    val database = remember { AppDatabase.getDatabase(context) }
    val viewModel: ProjectViewModel = viewModel(
        factory = ProjectViewModel.Factory(database.projectDao())
    )

    val projects by viewModel.projects.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(all = 28.dp)
    ){
        items(projects) { project ->
            ProjectCard(project = project, navController = navController)
        }
    }
}

