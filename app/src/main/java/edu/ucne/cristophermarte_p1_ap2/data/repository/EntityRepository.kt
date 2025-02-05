package edu.ucne.cristophermarte_p1_ap2.data.repository

import edu.ucne.cristophermarte_p1_ap2.data.local.dao.SistemaDao
import edu.ucne.cristophermarte_p1_ap2.data.local.entity.Sistema
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EntityRepository @Inject constructor(
    private val sistemaDao: SistemaDao
) {
    suspend fun saveEntity(sistema: Sistema) = sistemaDao.save(sistema)

    suspend fun find(id: Int): Sistema? =  sistemaDao.find(id)

    suspend fun delete(sistema: Sistema) =  sistemaDao.delete(sistema)

    fun getAll(): Flow<List<Sistema>> = sistemaDao.getAll()
}