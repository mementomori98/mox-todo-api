package mox.todo.core.repositories

interface Repository<TKey, TEntity> {

    fun add(model: TEntity): TEntity
    fun single(key: TKey): TEntity
    fun singleOrNull(key: TKey): TEntity?
    fun single(filter: (TEntity) -> Boolean): TEntity
    fun singleOrNull(filter: (TEntity) -> Boolean): TEntity?
    fun all(): List<TEntity>
    fun all(filter: (TEntity) -> Boolean): List<TEntity>
    fun update(model: TEntity): TEntity
    fun delete(key: TKey)
    fun deleteAll(filter: (TEntity) -> Boolean)

}