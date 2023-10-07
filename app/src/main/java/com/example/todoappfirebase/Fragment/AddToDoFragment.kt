package com.example.todoappfirebase.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.todoappfirebase.R
import com.example.todoappfirebase.databinding.FragmentAddToDoBinding
import com.example.todoappfirebase.ultils.adapter.ToDoData
import com.google.android.material.textfield.TextInputEditText

class AddToDoFragment : DialogFragment() {
    private lateinit var binding:FragmentAddToDoBinding
    private lateinit var listener: DialogNextBtnClickListener
    private var toDoData: ToDoData?= null
    fun setListener(listener: DialogNextBtnClickListener){
        this.listener = listener
    }
    companion object{
        const val TAG = "AddToDoPopupFragment"

        @JvmStatic
        fun newInstance(taskId:String, task:String) = AddToDoFragment().apply {
            arguments = Bundle().apply {
                putString("taskId", taskId)
                putString("task", task)
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentAddToDoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null){
            toDoData = ToDoData(arguments?.getString("taskId").toString(),arguments?.getString("task").toString())
        }
        binding.edtTodo.setText(toDoData?.task)
        registerEvents()
    }

    private fun registerEvents() {
        binding.btnNext.setOnClickListener {
            val todoTask = binding.edtTodo.text.toString()
            if(todoTask.isNotEmpty()){
                if(toDoData == null){
                    listener.onSaveTask(todoTask, binding.edtTodo)
                }else{
                    toDoData?.task = todoTask
                    listener.onUpdateTask(toDoData!!, binding.edtTodo)
                }

            }else{
                Toast.makeText(context,"Please type your task!!!", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }

    interface DialogNextBtnClickListener{
        fun onSaveTask(todo: String, todoEt: TextInputEditText)
        fun onUpdateTask(toDoData: ToDoData, todoEt: TextInputEditText)
    }
}