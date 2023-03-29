package com.example.innobuzzassignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility

class ContentAdapter(private val postList : ArrayList<Post>) : RecyclerView.Adapter<ContentAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(itemView)
    }
//    var onItemClick : ((Post)->Unit)? = null
    override fun onBindViewHolder(holder:MyViewHolder, position:Int){

        val currentItem = postList[position]
        holder.body.setText("id "+currentItem.id + "\n" + "user id " + currentItem.userId + "\n" + "title " + currentItem.title + "\n" + "body " + currentItem.body)
        holder.itemView.setOnClickListener(View.OnClickListener {
            val content:FragmentContainerView = holder.itemView.findViewById<FragmentContainerView>(R.id.content)
            content.visibility = if (content.visibility == View.VISIBLE){
                View.INVISIBLE
            } else{
                View.VISIBLE
            }
        })
    }
    override fun getItemCount(): Int {
        return postList.size
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title:TextView = itemView.findViewById<TextView>(R.id.title)
        val content:FragmentContainerView = itemView.findViewById<FragmentContainerView>(R.id.content)
        val body:TextView = content.findViewById<TextView>(R.id.body)

    }
}