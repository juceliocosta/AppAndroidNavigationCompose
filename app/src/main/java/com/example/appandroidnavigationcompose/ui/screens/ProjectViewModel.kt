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

class ProjectViewModel(
    private val repository: ProjectRepository,
) : ViewModel() {
    private val _projects = MutableStateFlow<List<Project>>(emptyList())
    val projects: StateFlow<List<Project>> = _projects.asStateFlow()

    init { fetchProjects() }

    private fun fetchProjects() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _projects.value = repository.getAllProjects()
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    class Factory(private val repository: ProjectRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProjectViewModel(repository) as T
        }
    }
}