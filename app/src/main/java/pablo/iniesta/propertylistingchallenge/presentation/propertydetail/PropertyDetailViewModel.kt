package pablo.iniesta.propertylistingchallenge.presentation.propertydetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pablo.iniesta.propertylistingchallenge.data.api.responses.PropertyDetail
import pablo.iniesta.propertylistingchallenge.domain.PropertiesRepository
import pablo.iniesta.propertylistingchallenge.util.Resource
import javax.inject.Inject

@HiltViewModel
class PropertyDetailViewModel @Inject constructor(
    private val repository: PropertiesRepository
) : ViewModel() {

    val propertyDetail: MutableLiveData<Resource<PropertyDetail>> = MutableLiveData()

    init {
        getPropertyDetail()
    }

    private fun getPropertyDetail() {
        propertyDetail.postValue(Resource.Loading())
        viewModelScope.launch {
            propertyDetail.postValue(repository.getPropertyDetail())
        }
    }
}