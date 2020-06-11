package mox.todo.core.models

abstract class Entity<TKey>(open var key: TKey) {
    abstract val valid: Boolean
}