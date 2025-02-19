package pablo.iniesta.propertylistingchallenge.presentation.propertylist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pablo.iniesta.propertylistingchallenge.data.db.PropertyEntity
import pablo.iniesta.propertylistingchallenge.domain.PropertiesRepository
import pablo.iniesta.propertylistingchallenge.util.Resource
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class PropertyListViewModel @Inject constructor(
    private val repository: PropertiesRepository
) : ViewModel() {

    val properties: MutableLiveData<Resource<List<PropertyEntity>>> = MutableLiveData()

    init {
        getProperties()
    }

    private fun getProperties() {
        Log.d("XXX", "GET PROPERTIES")
        properties.postValue(Resource.Loading())
        viewModelScope.launch {
            properties.postValue(repository.getProperties())
        }
    }

    fun updateFavoritedProperty(property: PropertyEntity, isFav: Boolean) {
        viewModelScope.launch {
            repository.updateProperty(
                property.copy(
                    isFavorite = isFav,
                    favoritedDate = if (isFav) Date() else null
                )
            )
            properties.postValue(repository.getProperties())
        }
    }
}