package com.example.final30

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final30.RecyclerView.TaskAdapter
import com.example.final30.ui.theme.Final30Theme

class MainActivity : ComponentActivity() {
    private val taskManager = TaskManager()
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val taskInput = findViewById<EditText>(R.id.taskInput)
        val DescriptionInput = findViewById<EditText>(R.id.DescriptionInput)
        val addTaskButton = findViewById<Button>(R.id.addTaskButton)
        val taskRecyclerView = findViewById<RecyclerView>(R.id.taskRecyclerView)

        taskAdapter = TaskAdapter(taskManager.getTasksSortedByName()) {taskId ->
            taskManager.completeTask(taskId)
            updateTasks()
        }

        taskRecyclerView.layoutManager = LinearLayoutManager(this)
        taskRecyclerView.adapter = taskAdapter

        addTaskButton.setOnClickListener{
            val taskName = taskInput.text.toString()
            val taskDescription = DescriptionInput.text.toString()
            if (taskName.isNotBlank()){
                taskManager.addTask(taskName, taskDescription)
                taskInput.text.clear()
                updateTasks()
            }
        }
    }

    private fun updateTasks(){
        taskAdapter = TaskAdapter(taskManager.getTasksSortedByName()) { taskId ->
            taskManager.completeTask(taskId)
            updateTasks()
        }
        findViewById<RecyclerView>(R.id.taskRecyclerView).adapter = taskAdapter
    }
}