package edu.ucne.cristophermarte_p1_ap2.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.cristophermarte_p1_ap2.data.local.dao.SistemaDao
import edu.ucne.cristophermarte_p1_ap2.data.local.entity.SistemaEntity

@Database(
    entities = [
        SistemaEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class CristopherMarteP1Db : RoomDatabase() {
    abstract fun entityDao(): SistemaDao
}