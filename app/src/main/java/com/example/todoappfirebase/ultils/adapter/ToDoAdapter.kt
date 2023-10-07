package com.example.todoappfirebase.ultils.todoadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappfirebase.R
import com.example.todoappfirebase.databinding.EachTodoItemBinding
import com.example.todoappfirebase.ultils.adapter.ToDoData

class ToDoAdapter(private val list: MutableList<ToDoData>): RecyclerView.Adapter<ToDoAdapter.myViewHolder>() {
    private var listener: ToDoAdapterClickInterface? = null
    fun setListener(listener: ToDoAdapterClickInterface){
        this.listener = listener
    }
    inner class myViewHolder(val binding:EachTodoItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val binding = EachTodoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return myViewHolder(binding)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        with(holder){
            with(list[position]){
                binding.todoTask.text = this.task

                binding.taskDelete.setOnClickListener {
                    listener?.onDeleteClicked(this)
                }

                binding.taskEdit.setOnClickListener {
                    listener?.onEditClicked(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface ToDoAdapterClickInterface{
        fun onDeleteClicked(toDoData: ToDoData)
        fun onEditClicked(toDoData: ToDoData)
    }
}