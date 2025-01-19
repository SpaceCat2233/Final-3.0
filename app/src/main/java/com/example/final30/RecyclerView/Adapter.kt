package com.example.final30.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.compose.ui.layout.Layout
import androidx.recyclerview.widget.RecyclerView
import com.example.final30.R
import com.example.final30.Task

class TaskAdapter(private val tasks: List<Task>, private val onComplete: (Int) -> Unit) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){



        class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val taskName: TextView = itemView.findViewById(R.id.taskName)
            val taskDescription: TextView = itemView.findViewById((R.id.taskDescription))
            val completeButton: Button = itemView.findViewById(R.id.completeButton)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item,parent,false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.taskName.text = task.name
        holder.taskDescription.text = task.description
        holder.completeButton.isEnabled = !task.isCompleted
        holder.completeButton.setOnClickListener {
            onComplete(task.id)

        }
    }

    override fun getItemCount() = tasks.size
}