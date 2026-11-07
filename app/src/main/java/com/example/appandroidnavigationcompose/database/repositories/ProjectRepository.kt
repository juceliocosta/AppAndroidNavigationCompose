package com.example.appandroidnavigationcompose.database.repositories

import com.example.appandroidnavigationcompose.database.dao.ProjectDao
import com.example.appandroidnavigationcompose.database.entities.Project
import com.example.appandroidnavigationcompose.network.ProjectApiService

class ProjectRepository(
    private val api: ProjectApiService,
    private val projectDao: ProjectDao
) {
    suspend fun getAllProjects(): List<Project> {
        return try {
            val projects = api.getAllProjects()
            projectDao.insertAll(projects)
            projects
        } catch (e: Exception) {
            projectDao.getAllProjects()
        }
    }

    suspend fun getLocalProjects(): List<Project> {
        return projectDao.getAllProjects()
    }

    suspend fun getProjectById(id: Int): Project? {
        return projectDao.getProjectById(id)
    }
}
