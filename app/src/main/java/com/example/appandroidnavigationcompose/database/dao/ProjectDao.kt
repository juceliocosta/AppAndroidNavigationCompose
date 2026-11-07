package com.example.appandroidnavigationcompose.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appandroidnavigationcompose.database.entities.Project

@Dao
interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: Project)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(projects: List<Project>)

    @Query("SELECT * FROM projects ORDER BY project_name ASC")
    suspend fun getAllProjects(): List<Project>

}