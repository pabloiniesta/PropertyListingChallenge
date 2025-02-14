package pablo.iniesta.propertylistingchallenge.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pablo.iniesta.propertylistingchallenge.data.api.PropertiesApi
import pablo.iniesta.propertylistingchallenge.domain.PropertiesRepository
import pablo.iniesta.propertylistingchallenge.util.Const.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePropertiesRepository(
        propertiesApi: PropertiesApi
    ) = PropertiesRepository(propertiesApi)

    @Singleton
    @Provides
    fun providePropertiesApi(): PropertiesApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PropertiesApi::class.java)
    }
}