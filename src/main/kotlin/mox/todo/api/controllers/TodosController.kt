package mox.todo.api.controllers

import mox.todo.api.models.TodoApiModel
import mox.todo.core.repositories.TodoListRepository
import mox.todo.core.repositories.TodoRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/todos")
class TodosController(
    val todoRepository: TodoRepository,
    val listRepository: TodoListRepository
) {

    @PostMapping
    fun add(@RequestBody todo: TodoApiModel): TodoApiModel {
        return TodoApiModel(
            todoRepository.add(
                todo.makeModel(listKeyFinder = { listName -> if (listName == null) null else listRepository.single(listName).key })
            ), listRepository.singleOrNull { it.name == todo.list }
        )
    }

    @GetMapping
    fun getAll(@RequestParam(name = "listName") list: String?): List<TodoApiModel> = todoRepository
        .all().map { todo ->
            TodoApiModel(todo, listRepository.singleOrNull(todo.listId ?: -1))
        }.filter { if (list == null) true else it.list == list}
}