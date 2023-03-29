package com.example.innobuzzassignment

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "post")
data class Post(
    @PrimaryKey(autoGenerate = true)
    val id:Integer , val userId:Integer, val title:String, val body:String
    )
