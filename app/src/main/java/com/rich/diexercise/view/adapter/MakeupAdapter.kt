package com.rich.diexercise.view.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rich.diexercise.databinding.ItemMakeupBinding
import com.rich.diexercise.model.ResponseItem

class MakeupAdapter(var listMakeup : List<ResponseItem>): RecyclerView.Adapter<MakeupAdapter.ViewHolder>() {
    var onDelete : ((ResponseItem)->Unit)? = null
    var onDetail : ((ResponseItem)->Unit)? = null

    class ViewHolder(var binding : ItemMakeupBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MakeupAdapter.ViewHolder {
        var view = ItemMakeupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MakeupAdapter.ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.binding.name.text = listMakeup!![position].name
        holder.binding.brand.text = listMakeup!![position].brand
        holder.binding.price.text = listMakeup!![position].priceSign + listMakeup!![position].price.toString()
        Glide.with(holder.itemView.context)
            .load(listMakeup!![position].imageLink)
            .into(holder.binding.img)

        holder.binding.deleteCar.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val bun = Bundle()
                bun.putString("id", listMakeup[position].id.toString())
            }
        })

    }

    override fun getItemCount(): Int {

        return listMakeup.size

    }
}