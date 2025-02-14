package pablo.iniesta.propertylistingchallenge.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pablo.iniesta.propertylistingchallenge.data.api.PropertiesApi
import pablo.iniesta.propertylistingchallenge.data.db.PropertiesDao
import pablo.iniesta.propertylistingchallenge.data.db.PropertiesDatabase
import pablo.iniesta.propertylistingchallenge.data.db.PropertyTypeConverters
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
    fun providePropertyDatabase(
        @ApplicationContext context: Context
    ): PropertiesDatabase {
        return Room.databaseBuilder(
            context,
            PropertiesDatabase::class.java,
            "property_database"
        )
            .addTypeConverter(PropertyTypeConverters())
            .build()
    }

    @Singleton
    @Provides
    fun providePropertyDao(propertiesDatabase: PropertiesDatabase): PropertiesDao {
        return propertiesDatabase.propertyDao()
    }

    @Singleton
    @Provides
    fun providePropertiesRepository(
        propertiesApi: PropertiesApi,
        propertiesDao: PropertiesDao
    ) = PropertiesRepository(propertiesApi, propertiesDao)

    @Singleton
    @Provides
    fun providePropertiesApi(): PropertiesApi {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(PropertiesApi::class.java)
    }
}