package com.example.fotografpaylasmafirebase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.fotografpaylasmafirebase.R
import com.example.fotografpaylasmafirebase.model.Post
import com.squareup.picasso.Picasso

class HaberRecyclerAdapter(val postList:ArrayList<Post>) : RecyclerView.Adapter<HaberRecyclerAdapter.PostHolder>(){

    private lateinit var kullaniciEmail:TextView
    private lateinit var kullaniciYorum:TextView
    private lateinit var gorselView:ImageView

    class PostHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
       val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_row,parent,false)
        return PostHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        kullaniciEmail = holder.itemView.findViewById<TextView?>(R.id.recycler_row_kullanici_email)
        kullaniciYorum = holder.itemView.findViewById<TextView?>(R.id.recycler_row_yorum_text)
        gorselView = holder.itemView.findViewById<ImageView>(R.id.recycler_row_imageview)

        kullaniciEmail.text = postList[position].kullaniciEmail
        kullaniciYorum.text = postList[position].kullaniciYorum
        Picasso.get().load(postList[position].gorselUrl).into(gorselView)

    }

}