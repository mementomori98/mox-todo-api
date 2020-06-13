package mox.todo.api.controllers

import mox.todo.api.models.TodoListApiModel
import mox.todo.core.repositories.TodoListRepository
import mox.todo.core.repositories.TodoRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/lists")
class TodoListsController(
    val listRepository: TodoListRepository,
    val todoRepository: TodoRepository
): ControllerBase() {

    @PostMapping
    fun create(@RequestBody list: TodoListApiModel) = TodoListApiModel(
        listRepository.add(list.makeModel("J1bk9TejCNYv3xXh9zqWB488b1r1"))
    )

    @GetMapping
    fun getAll(): List<TodoListApiModel> = listRepository.all { it.userId == userId() }.map {
        TodoListApiModel(it)
    }

    @DeleteMapping
    @RequestMapping("/{key}")
    fun delete(@PathVariable(name = "key") key: Int) {
        if (listRepository.single(key).userId != userId()) return
        todoRepository.deleteAll { it.listId == key }
        listRepository.delete(key)
    }

}