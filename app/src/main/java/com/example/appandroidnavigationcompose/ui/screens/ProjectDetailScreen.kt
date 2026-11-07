package com.example.appandroidnavigationcompose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appandroidnavigationcompose.database.AppDatabase
import com.example.appandroidnavigationcompose.database.repositories.ProjectRepository
import com.example.appandroidnavigationcompose.network.RetrofitInstance

@Composable
fun ProjectDetailScreen(
    projectId: Int,
    navController: NavController,
    viewModel: ProjectDetailViewModel = viewModel(
        factory = ProjectDetailViewModel.Factory(
            ProjectRepository(RetrofitInstance.api, AppDatabase.getDatabase(LocalContext.current).projectDao()),
            projectId
        )
    )
) {
    val project by viewModel.project.collectAsState()

    project?.let { item ->
        Column(
            modifier = Modifier.fillMaxSize().padding(28.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = item.name, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Linguagem: ${item.language ?: "Não informada"}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = item.description ?: "Sem descrição",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Voltar")
            }
        }
    }
}
