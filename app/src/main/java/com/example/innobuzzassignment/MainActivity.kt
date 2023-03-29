package com.example.innobuzzassignment

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val url = "https://jsonplaceholder.typicode.com/"
    private lateinit var postDatabase:PostDatabase
    private lateinit var postAdapter:ContentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        postDatabase = Room.databaseBuilder(applicationContext, PostDatabase::class.java,"postDB").build()
        val post_list = findViewById<RecyclerView>(R.id.post_list)
        post_list.layoutManager = LinearLayoutManager(this)
        post_list.setHasFixedSize(true)
        val posts : ArrayList<Post> = ArrayList<Post>()
        //make an instance of retrofit
        val retrofitInstance = Retrofit.Builder().baseUrl(url).addConverterFactory(
            GsonConverterFactory.create()).build()

        val api = retrofitInstance.create<Api>()
        val call = api.getInstances()
        call.enqueue(object : Callback<List<Model>?> {
            override fun onResponse(call: Call<List<Model>?>, response: Response<List<Model>?>) {
                val responseBody = response.body()
                //Toast.makeText(applicationContext,responseBody.get(0),Toast.LENGTH_SHORT).show()
                if (responseBody != null) {
                    Log.i(TAG, "onResponse: -----------------------------"+responseBody.get(0))
                }
                if (responseBody != null) {
                    for(myData in responseBody) {
                        GlobalScope.launch {
                            //fetch data from url and put it inside roomDatabase
                            postDatabase.postDao().insertPost(
                                Post(myData.id, myData.userId, myData.title, myData.body)
                            )
                        }
                        posts.add(Post(myData.id, myData.userId, myData.title, myData.body))
                    }
                    postAdapter = ContentAdapter(posts)
                    post_list.adapter = postAdapter

//                    postAdapter.onItemClick={
//
//                    }
                }
//                postDatabase.postDao().getAllStudent().observe(this,{
//
//                })
            }

            override fun onFailure(call: Call<List<Model>?>, t: Throwable) {
                Log.i("failure","Retrofit response not received")
            }
        })
    }
}
