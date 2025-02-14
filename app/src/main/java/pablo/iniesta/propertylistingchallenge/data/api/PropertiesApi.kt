package pablo.iniesta.propertylistingchallenge.data.api

import pablo.iniesta.propertylistingchallenge.data.api.responses.PropertyDetail
import pablo.iniesta.propertylistingchallenge.data.api.responses.PropertyList
import retrofit2.http.GET

interface PropertiesApi {

    @GET("list.json")
    suspend fun getProperties(): PropertyList

    @GET("detail.json")
    suspend fun getPropertyDetail(): PropertyDetail

}