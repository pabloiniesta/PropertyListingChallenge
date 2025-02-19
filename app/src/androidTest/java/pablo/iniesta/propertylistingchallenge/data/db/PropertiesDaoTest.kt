package pablo.iniesta.propertylistingchallenge.data.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import pablo.iniesta.propertylistingchallenge.data.api.responses.Price
import pablo.iniesta.propertylistingchallenge.data.api.responses.PriceInfo

@RunWith(AndroidJUnit4::class)
@SmallTest
class PropertiesDaoTest {

    private lateinit var database: PropertiesDatabase
    private lateinit var dao: PropertiesDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PropertiesDatabase::class.java
        ).addTypeConverter(PropertyTypeConverters()).allowMainThreadQueries().build()
        dao = database.propertyDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertProperties() = runTest {
        dao.insertProperties(mockProperties)
        val properties = dao.getProperties()
        assertThat(properties).isEqualTo(mockProperties)
    }

    @Test
    fun updateProperty() = runTest {
        dao.insertProperties(mockProperties)
        val updatedProperty = mockProperties.first().copy(isFavorite = true)
        dao.updateProperty(updatedProperty)
        val properties = dao.getProperties()
        assertThat(properties.first().isFavorite).isTrue()
    }

    @Test
    fun isDatabaseEmptyReturnsTrueWhenDatabaseIsEmpty() = runTest {
        assertThat(dao.isDatabaseEmpty()).isTrue()
    }

    @Test
    fun isDatabaseEmptyReturnsFalseWhenDatabaseIsNotEmpty() = runTest {
        dao.insertProperties(mockProperties)
        assertThat(dao.isDatabaseEmpty()).isFalse()
    }

    private val mockProperties = listOf(
        PropertyEntity(
            "1",
            "thumbnail",
            PriceInfo(Price(100000.0, "$")),
            100.0,
            2,
            "address",
            "district",
            "neighborhood",
            false,
            null
        )
    )
}