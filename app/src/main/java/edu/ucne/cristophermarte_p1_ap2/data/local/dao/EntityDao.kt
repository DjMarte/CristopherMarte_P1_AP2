package edu.ucne.cristophermarte_p1_ap2.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.cristophermarte_p1_ap2.data.local.entity.Entity
import kotlinx.coroutines.flow.Flow

@Dao
interface EntityDao {
    @Upsert()
    suspend fun save(entity: Entity)

    @Query(
        """
            SELECT * FROM Entity
            WHERE entityId=:id
            LIMIT 1
        """
    )
    suspend fun find(id: Int): Entity?

    @Delete
    suspend fun delete(entity: Entity)

    @Query("SELECT * FROM Entity")
    fun getAll(): Flow<List<Entity>>
}