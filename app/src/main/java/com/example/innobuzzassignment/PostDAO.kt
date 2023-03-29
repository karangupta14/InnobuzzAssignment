package com.example.innobuzzassignment

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

//Database Access Object
@Dao
interface PostDAO {
    @Insert
    fun insertPost(post:Post)

    @Delete
    fun deletePost(post:Post)

    @Query("SELECT * FROM post")
    fun getAllStudent(): LiveData<List<Post>>
}