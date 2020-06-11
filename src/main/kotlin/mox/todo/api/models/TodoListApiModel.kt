package mox.todo.api.models

import mox.todo.core.models.TodoList

data class TodoListApiModel(
    val name: String,
    val color: Int
) {

    constructor(list: TodoList) : this(list.name, list.color)

    fun makeModel(userId: String): TodoList = TodoList(name, color, userId)

}