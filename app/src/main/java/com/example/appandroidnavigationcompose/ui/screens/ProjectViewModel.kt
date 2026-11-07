package com.example.appandroidnavigationcompose.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.appandroidnavigationcompose.database.dao.ProjectDao
import com.example.appandroidnavigationcompose.database.entities.Project
import com.example.appandroidnavigationcompose.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProjectViewModel(
    private val projectDao: ProjectDao,
) : ViewModel() {
    private val _projects = MutableStateFlow<List<Project>>(emptyList())
    val projects: StateFlow<List<Project>> = _projects.asStateFlow()

    init { fetchAndSaveProjects() }

    fun fetchAndSaveProjects() {
        viewModelScope.launch {
            // 1. Carrega do banco de dados local primeiro
            val localProjects = withContext(Dispatchers.IO) {
                projectDao.getAllProjects()
            }
            _projects.value = localProjects

            // 2. Busca da API e atualiza o banco local
            try {
                val apiProjects = withContext(Dispatchers.IO){
                    RetrofitInstance.api.getAllProjects()
                }
                withContext(Dispatchers.IO) {
                    projectDao.insertAll(apiProjects)
                }

                // 3. Atualiza a tela com os dados mais recentes do banco
                _projects.value = withContext(Dispatchers.IO) {
                    projectDao.getAllProjects()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    class Factory(private val projectDao: ProjectDao) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProjectViewModel(projectDao) as T
        }
    }
}