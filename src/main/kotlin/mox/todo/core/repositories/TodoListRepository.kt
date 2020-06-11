package mox.todo.core.repositories

import mox.todo.core.models.TodoList

interface TodoListRepository : Repository<Int, TodoList> {
    fun single(name: String): TodoList
}