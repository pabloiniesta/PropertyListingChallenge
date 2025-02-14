package pablo.iniesta.propertylistingchallenge.domain

import pablo.iniesta.propertylistingchallenge.data.api.PropertiesApi
import pablo.iniesta.propertylistingchallenge.data.api.responses.PropertyDetail
import pablo.iniesta.propertylistingchallenge.data.api.responses.PropertyList
import pablo.iniesta.propertylistingchallenge.data.db.PropertiesDao
import pablo.iniesta.propertylistingchallenge.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PropertiesRepository @Inject constructor(
    private val propertiesApi: PropertiesApi,
    private val propertiesDao: PropertiesDao
){

    suspend fun getProperties(): Resource<PropertyList> {
        val response = try {
            propertiesApi.getProperties()
        } catch (e: Exception) {
            return Resource.Error(e.message ?: "An unknown error occurred")
        }
        return Resource.Success(response)
    }

    suspend fun getPropertyDetail(): Resource<PropertyDetail> {
        val response = try {
            propertiesApi.getPropertyDetail()
        } catch (e: Exception) {
            return Resource.Error(e.message ?: "An unknown error occurred")
        }
        return Resource.Success(response)
    }
}