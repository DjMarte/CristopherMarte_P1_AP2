package edu.ucne.cristophermarte_p1_ap2.data.repository

import edu.ucne.cristophermarte_p1_ap2.data.local.dao.SistemaDao
import edu.ucne.cristophermarte_p1_ap2.data.local.entity.SistemaEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SistemaRepository @Inject constructor(
    private val sistemaDao: SistemaDao
) {
    suspend fun saveSistema(sistemaEntity: SistemaEntity) = sistemaDao.save(sistemaEntity)

    suspend fun find(id: Int): SistemaEntity? =  sistemaDao.find(id)

    suspend fun delete(sistemaEntity: SistemaEntity) =  sistemaDao.delete(sistemaEntity)

    fun getAll(): Flow<List<SistemaEntity>> = sistemaDao.getAll()
}