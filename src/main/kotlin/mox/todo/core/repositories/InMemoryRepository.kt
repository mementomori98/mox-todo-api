package mox.todo.core.repositories

import mox.todo.core.models.Entity
import kotlin.IllegalArgumentException

abstract class InMemoryRepository<TKey: Any, TEntity: Entity<TKey>>(val keyProvider: (TKey?) -> TKey)
    : Repository<TKey, TEntity> {

    private val entities: MutableList<TEntity> = ArrayList()
    private var key: TKey = keyProvider(null)

    override fun add(model: TEntity, position: Int): TEntity {
        validate(model)
        key = keyProvider(key)
        model.key = key

        entities.add(
            if (position >= entities.size) 0 else position,
            model
        )
        return model
    }

    override fun single(key: TKey) = single { it.key == key }

    override fun singleOrNull(key: TKey) = singleOrNull { it.key == key }

    override fun single(filter: (TEntity) -> Boolean) = singleOrNull(filter)
        ?: throw IllegalArgumentException("No entities match the giver criteria")

    override fun singleOrNull(filter: (TEntity) -> Boolean): TEntity? {
        if (all(filter).size > 1)
            throw IllegalArgumentException("Multiple entities match the given criteria")
        return all(filter).firstOrNull()
    }

    override fun all(): List<TEntity> = entities

    override fun all(filter: (TEntity) -> Boolean): List<TEntity> = entities.filter(filter)

    override fun update(model: TEntity): TEntity {
        single(model.key)
        validate(model)
        entities.replaceAll { e -> if (e.key == model.key) model else e }
        return model
    }

    override fun delete(key: TKey) {
        single(key)
        entities.removeIf { it.key == key }
    }

    override fun deleteAll(filter: (TEntity) -> Boolean) {
        entities.removeIf(filter)
    }

    protected open fun validate(model: TEntity) {
        if (!model.valid)
            throw IllegalStateException("Model is not valid")
    }

}