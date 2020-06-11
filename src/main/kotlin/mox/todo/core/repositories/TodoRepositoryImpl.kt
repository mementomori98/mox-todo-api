package mox.todo.core.repositories

import mox.todo.core.models.Todo
import org.springframework.stereotype.Component
import kotlin.IllegalArgumentException

@Component
final class TodoRepositoryImpl : TodoRepository, InMemoryRepository<Int, Todo>(
    { key -> if (key == null) 0 else key + 1 }
) {

    init {
        add(Todo("Ask for vacation", "July 1st to 15th", 3, 1))
        add(Todo("Ask for a raise", "", 5, 1))

        add(Todo("ADS", "Thursday 9-12", 2, 2))
        add(Todo("DAI", "17th Wednesday 9.00 - 18th Thursday 9.00", 3, 2))
        add(Todo("AND", "Sunday 23.59", 4, 2))

        add(Todo("Tomatoes", "Organic is better", 1, 3))
        add(Todo("Bell peppers", "", 1, 3))
        add(Todo("Salt & Pepper", "", 1, 3))
        add(Todo("Rice", "", 1, 3))
        add(Todo("Pasta", "", 1, 3))
        add(Todo("Muesli", "", 1, 3))

        add(Todo("Call mom", "", 1, 4))
        add(Todo("Call dad", "", 1, 4))
        add(Todo("Do laundry", "", 1, 4))
        add(Todo("Watch Clean code", "", 1, 4))
        add(Todo("Go on a walk with Ralu", "", 1, 4))
    }

}