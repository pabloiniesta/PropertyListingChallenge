package pablo.iniesta.propertylistingchallenge.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface PropertiesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProperties(properties: List<PropertyEntity>)

    //TODO more functions
}