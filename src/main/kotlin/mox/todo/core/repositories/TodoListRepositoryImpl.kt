package mox.todo.core.repositories

import ch.qos.logback.core.spi.LifeCycle
import mox.todo.core.models.TodoList
import org.springframework.stereotype.Component
import java.lang.IllegalArgumentException

@Component
final class TodoListRepositoryImpl : TodoListRepository, InMemoryRepository<Int, TodoList>(
    { key -> if (key == null) 0 else key + 1 }
) {

    init {
        add(TodoList("Work", userId = "J1bk9TejCNYv3xXh9zqWB488b1r1"))
        add(TodoList("School", 1, "J1bk9TejCNYv3xXh9zqWB488b1r1"))
        add(TodoList("Shopping", 2, "J1bk9TejCNYv3xXh9zqWB488b1r1"))
        add(TodoList("Miscellaneous", 3, "J1bk9TejCNYv3xXh9zqWB488b1r1"))
    }

    override fun single(name: String): TodoList = single { it.name == name }

    override fun validate(model: TodoList) {
        super.validate(model)
        if (all { it.name == model.name && it.userId == model.userId }.isNotEmpty())
            throw IllegalArgumentException("There is already a list with this name ${model.name} for this user")
    }
}