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
): ControllerBase() {

    @PostMapping
    fun add(@RequestBody todo: TodoApiModel): TodoApiModel {
        val model = todo.makeModel { name -> listRepository.single{it.name == name && it.userId == userId()}.key }
        return TodoApiModel(
            todoRepository.add(model, todo.position),
            listRepository.singleOrNull { it.name == todo.list && it.userId == userId() }
        )
    }

    @GetMapping
    fun getAll(@RequestParam(name = "listName") list: String?): List<TodoApiModel> = todoRepository
        .all { it.listId in listRepository.all { it.userId == userId() }.map { list -> list.key }}.map { todo ->
            TodoApiModel(todo, listRepository.singleOrNull(todo.listId ?: -1))
        }.filter { if (list == null) true else it.list == list }

    @PatchMapping
    fun update(@RequestBody todo: TodoApiModel): TodoApiModel {
        val model = todo.makeModel { listName -> listRepository.single { it.name == listName && it.userId == userId() }.key
        }
        return TodoApiModel(
            todoRepository.update(model),
            listRepository.singleOrNull {it.name == todo.list && it.userId == userId()}
        )
    }

    @DeleteMapping
    @RequestMapping("/{key}")
    fun delete(@PathVariable("key") key: Int) = todoRepository.delete(key)
}