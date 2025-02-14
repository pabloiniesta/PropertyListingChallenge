package pablo.iniesta.propertylistingchallenge.presentation.propertylist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pablo.iniesta.propertylistingchallenge.domain.PropertiesRepository
import pablo.iniesta.propertylistingchallenge.util.Resource
import javax.inject.Inject

@HiltViewModel
class PropertyListViewModel @Inject constructor(
    private val repository: PropertiesRepository
) : ViewModel() {

    fun getProperties() {
        viewModelScope.launch {
            when(val response = repository.getProperties()) {
                is Resource.Error -> Log.d("XXX", "ERROR")
                is Resource.Loading -> Log.d("XXX", "Loading")
                is Resource.Success -> Log.d("XXX", "SUCCSESS: " + response.data)
            }
        }
    }
}