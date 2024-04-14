package com.mr.myroom.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mr.myroom.databinding.LayoutCardBinding
import com.mr.myroom.listener.Listener
import com.mr.myroom.model.UsersItem

class RoomAdapter(
    private val context: Context,
    private val listener: Listener
) :
    RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {
    private lateinit var binding: LayoutCardBinding
    private var list1 = mutableListOf<UsersItem>()

    inner class RoomViewHolder(val binding: LayoutCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val inflator = LayoutInflater.from(context)
        binding = LayoutCardBinding.inflate(inflator, parent, false)
        return RoomViewHolder(binding)
    }

    override fun getItemCount(): Int {
        Log.d("SIZE_OF_THE_LIST", list1.size.toString())
        return list1.size
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val item = list1[position]
        holder.binding.txtFirstName.setText(item.firstName)
        holder.binding.txtEmail.setText(item.email)
        holder.binding.profileImg.load(item.image)
        holder.binding.imgDelete.apply {
            visibility = View.VISIBLE
            setOnClickListener {
                listener.onClick(item, 1, item.id!!)
                Log.d("ITEM_DELETED", "I am being clicked...")
            }
        }
        holder.binding.root.setOnClickListener {
            listener.onClick(item, 0, 0)
        }
    }

    fun updateList(listArray: List<UsersItem>) {
        list1.clear()
        list1.addAll(listArray)
        notifyDataSetChanged()
    }

}