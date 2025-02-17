package pablo.iniesta.propertylistingchallenge.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PropertiesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProperties(properties: List<PropertyEntity>)

    @Query("SELECT * FROM properties")
    suspend fun getProperties(): List<PropertyEntity>

    @Update
    suspend fun updateProperty(property: PropertyEntity)

    @Query("SELECT (SELECT COUNT(*) FROM properties) == 0")
    suspend fun isDatabaseEmpty(): Boolean

}