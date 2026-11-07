package com.example.appandroidnavigationcompose.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.appandroidnavigationcompose.database.entities.Project
import com.example.appandroidnavigationcompose.database.repositories.ProjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProjectDetailViewModel(
    private val repository: ProjectRepository,
    private val projectId: Int,
) : ViewModel() {
    private val _project = MutableStateFlow<Project?>(null)
    val project: StateFlow<Project?> = _project.asStateFlow()

    init { fetchProject() }

    private fun fetchProject() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _project.value = repository.getProjectById(projectId)
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    class Factory(private val repository: ProjectRepository, private val projectId: Int) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T = ProjectDetailViewModel(repository, projectId) as T
    }
}