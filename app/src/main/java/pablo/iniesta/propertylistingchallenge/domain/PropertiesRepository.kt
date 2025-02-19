package pablo.iniesta.propertylistingchallenge.domain

import pablo.iniesta.propertylistingchallenge.data.api.PropertiesApi
import pablo.iniesta.propertylistingchallenge.data.api.responses.PropertyDetail
import pablo.iniesta.propertylistingchallenge.data.api.responses.PropertyList
import pablo.iniesta.propertylistingchallenge.data.db.PropertiesDao
import pablo.iniesta.propertylistingchallenge.data.db.PropertyEntity
import pablo.iniesta.propertylistingchallenge.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PropertiesRepository @Inject constructor(
    private val propertiesApi: PropertiesApi,
    private val propertiesDao: PropertiesDao
) {

    suspend fun getProperties(): Resource<List<PropertyEntity>> {
        val propertyList: List<PropertyEntity>
        if (propertiesDao.isDatabaseEmpty()) {
            val response = try {
                propertiesApi.getProperties()
            } catch (e: Exception) {
                return Resource.Error(e.message ?: "An unknown error occurred")
            }
            propertyList = mapApiListToEntityList(response)
            propertiesDao.insertProperties(propertyList)
        } else {
            propertyList = propertiesDao.getProperties()
        }
        return Resource.Success(propertyList)
    }

    suspend fun updateProperty(property: PropertyEntity) {
        propertiesDao.updateProperty(property)
    }

    suspend fun getPropertyDetail(): Resource<PropertyDetail> {
        val response = try {
            propertiesApi.getPropertyDetail()
        } catch (e: Exception) {
            return Resource.Error(e.message ?: "An unknown error occurred")
        }
        return Resource.Success(response)
    }

    private fun mapApiListToEntityList(propertyList: PropertyList): List<PropertyEntity> {
        return propertyList.map { propertyListItem ->
            PropertyEntity(
                propertyCode = propertyListItem.propertyCode,
                thumbnail = propertyListItem.thumbnail,
                priceInfo = propertyListItem.priceInfo,
                size = propertyListItem.size,
                rooms = propertyListItem.rooms,
                address = propertyListItem.address,
                district = propertyListItem.district,
                neighborhood = propertyListItem.neighborhood,
                isFavorite = false
            )
        }
    }
}