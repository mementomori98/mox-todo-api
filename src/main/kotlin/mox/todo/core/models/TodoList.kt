package mox.todo.core.models

data class TodoList(
    val name: String,
    val color: Int = 0,
    val userId: String,
    override var key: Int = 0
): Entity<Int>(key) {
    override val valid get() = name != "" && color >= 0 && color <= 3
}