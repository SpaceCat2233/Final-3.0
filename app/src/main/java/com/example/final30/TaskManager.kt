package com.example.final30

class TaskManager {
    private val tasks = mutableListOf<Task>()
    private var nextId = 1

    fun addTask(name: String, description: String){
        tasks.add(Task(nextId++, name, description))
    }

    fun completeTask(id: Int){
        tasks.find {it.id == id }?.isCompleted = true
    }

    fun getTasksSortedByName(): List<Task> {
        return tasks.sortedBy{it.name}
    }

    fun getTasksSortedByStatus(): List<Task> {
        return tasks.sortedWith(compareBy<Task> {it.isCompleted}.thenBy{it.name})
    }
}