package mox.todo.api.controllers

import mox.todo.api.models.TodoListApiModel
import mox.todo.core.repositories.TodoListRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/lists")
class TodoListsController(
    val listRepository: TodoListRepository
) {

    @PostMapping
    fun create(@RequestBody list: TodoListApiModel) = TodoListApiModel(
        listRepository.add(list.makeModel("J1bk9TejCNYv3xXh9zqWB488b1r1"))
    )

    @GetMapping
    fun getAll(): List<TodoListApiModel> = listRepository.all().map {
        TodoListApiModel(it)
    }

}