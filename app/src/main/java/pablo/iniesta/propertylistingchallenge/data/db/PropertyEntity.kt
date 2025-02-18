package pablo.iniesta.propertylistingchallenge.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import pablo.iniesta.propertylistingchallenge.data.api.responses.PriceInfo
import java.util.Date

@Entity(tableName = "properties")
data class PropertyEntity(
    @PrimaryKey val propertyCode: String,
    val thumbnail: String,
    val priceInfo: PriceInfo,
    val size: Double,
    val rooms: Int,
    val address: String,
    val district: String,
    val neighborhood: String,
    val isFavorite: Boolean,
    val favoritedDate: Date? = null
)
