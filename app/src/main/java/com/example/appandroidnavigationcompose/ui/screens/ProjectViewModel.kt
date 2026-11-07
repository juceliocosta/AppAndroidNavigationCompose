package com.example.appandroidnavigationcompose.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appandroidnavigationcompose.database.dao.ProjectDao
import com.example.appandroidnavigationcompose.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProjectViewModel(
    private val projectDao: ProjectDao
) : ViewModel() {
    fun fetchAndSaveProjects() {
        viewModelScope.launch {
            try {
                val projects = withContext(Dispatchers.IO){
                    RetrofitInstance.api.getAllProjects()
                }
                withContext(Dispatchers.IO) {
                    projectDao.insertAll(projects)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}