package pablo.iniesta.propertylistingchallenge.data.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pablo.iniesta.propertylistingchallenge.data.api.responses.PriceInfo
import java.util.Date

@ProvidedTypeConverter
class PropertyTypeConverters {

    @TypeConverter
    fun fromPriceInfo(priceInfo: PriceInfo): String {
        return Gson().toJson(priceInfo)
    }

    @TypeConverter
    fun toPriceInfo(data: String): PriceInfo {
        val listType = object : TypeToken<PriceInfo>() {}.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}