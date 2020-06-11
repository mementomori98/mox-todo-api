package mox.todo.api.models

import mox.todo.core.models.TodoList

data class TodoListApiModel(
    val name: String = "",
    val color: Int = 0,
    val key: Int = 0
) {

    constructor(list: TodoList) : this(list.name, list.color, list.key)

    fun makeModel(userId: String): TodoList = TodoList(name, color, userId)

}