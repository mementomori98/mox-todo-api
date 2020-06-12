package mox.todo.api.models

import mox.todo.core.models.Todo
import mox.todo.core.models.TodoList

data class TodoApiModel(
    val title: String = "",
    val notes: String = "",
    val priority: Int = 1,
    val list: String? = null,
    val color: Int = 0,
    val key: Int = 0,
    val position: Int = 0
) {

    constructor(todo: Todo, list: TodoList?)
        : this(todo.title, todo.notes, todo.priority, list?.name, list?.color ?: 0, todo.key)

    fun makeModel(listKeyFinder: (String?) -> Int?) = Todo(title, notes, priority, listKeyFinder(list), key)
}