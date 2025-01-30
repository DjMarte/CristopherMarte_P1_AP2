package edu.ucne.cristophermarte_p1_ap2.data.repository

import edu.ucne.cristophermarte_p1_ap2.data.local.dao.EntityDao
import edu.ucne.cristophermarte_p1_ap2.data.local.entity.Entity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EntityRepository @Inject constructor(
    private val entityDao: EntityDao
) {
    suspend fun saveEntity(entity: Entity) = entityDao.save(entity)

    suspend fun find(id: Int): Entity? =  entityDao.find(id)

    suspend fun delete(tecnico: Entity) =  entityDao.delete(tecnico)

    fun getAll(): Flow<List<Entity>> = entityDao.getAll()
}