package com.example.appandroidnavigationcompose.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appandroidnavigationcompose.database.AppDatabase
import com.example.appandroidnavigationcompose.database.repositories.ProjectRepository
import com.example.appandroidnavigationcompose.network.RetrofitInstance

@Composable
fun ProjectListScreen(
    navController: NavController,
    viewModel: ProjectViewModel = viewModel(
        factory = ProjectViewModel.Factory(
            ProjectRepository(
                api = RetrofitInstance.api,
                projectDao = AppDatabase.getDatabase(LocalContext.current).projectDao()
            )
        )
    )
){

    val projects by viewModel.projects.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(all = 28.dp)
    ){
        items(projects) { project ->
            ProjectCard(project = project, navController = navController)
        }
    }
}

