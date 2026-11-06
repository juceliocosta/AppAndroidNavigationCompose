package com.example.appandroidnavigationcompose.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects")
data class Project(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "project_name")
    val name: String,
    @ColumnInfo(name = "project_description")
    val description: String,
    val language: String
)