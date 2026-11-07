package com.example.appandroidnavigationcompose.network

import com.example.appandroidnavigationcompose.database.entities.Project
import retrofit2.http.GET
import retrofit2.http.Path

interface ProjectApiService {
    @GET("repos")
    suspend fun getAllProjects(): List<Project>

    @GET("repos/{id}")
    suspend fun getAllProjectsById(@Path("id") projectId: Int): Project
}