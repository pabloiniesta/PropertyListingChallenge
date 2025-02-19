package pablo.iniesta.propertylistingchallenge.presentation.propertylist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pablo.iniesta.propertylistingchallenge.data.api.responses.Price
import pablo.iniesta.propertylistingchallenge.data.api.responses.PriceInfo
import pablo.iniesta.propertylistingchallenge.data.db.PropertyEntity
import pablo.iniesta.propertylistingchallenge.domain.PropertiesRepository
import pablo.iniesta.propertylistingchallenge.util.Resource

@OptIn(ExperimentalCoroutinesApi::class)
class PropertyListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: PropertyListViewModel
    private val propertiesRepository: PropertiesRepository = mockk()

    @Before
    fun setup() {
        coEvery { propertiesRepository.getProperties() } returns Resource.Success(mockProperties)
        viewModel = PropertyListViewModel(propertiesRepository)
    }

    @Test
    fun `check property list is retrieved successfully`() = runTest {
        advanceUntilIdle()

        val properties = viewModel.properties.value
        assertThat(properties).isInstanceOf(Resource.Success::class.java)
        val successProperties = (properties as Resource.Success).data
        assertThat(successProperties).isEqualTo(mockProperties)
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