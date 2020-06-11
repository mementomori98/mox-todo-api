package mox.todo.core.models

data class Todo(
    val title: String,
    val notes: String,
    val priority: Int,
    val listId: Int? = null,
    override var key: Int = 0
): Entity<Int>(key) {
    override val valid get() = title != "" && priority >= 1 && priority <= 5
}