package pablo.iniesta.propertylistingchallenge.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PropertyEntity::class], version = 2, exportSchema = false)
@TypeConverters(PropertyTypeConverters::class)
abstract class PropertiesDatabase : RoomDatabase() {
    abstract fun propertyDao(): PropertiesDao
}