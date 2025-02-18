package pablo.iniesta.propertylistingchallenge.presentation.propertydetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import dagger.hilt.android.AndroidEntryPoint
import pablo.iniesta.propertylistingchallenge.R
import pablo.iniesta.propertylistingchallenge.data.api.responses.PropertyDetail
import pablo.iniesta.propertylistingchallenge.databinding.FragmentPropertyDetailBinding
import pablo.iniesta.propertylistingchallenge.util.Resource

@AndroidEntryPoint
class PropertyDetailFragment : Fragment() {

    private val viewModel: PropertyDetailViewModel by viewModels()
    private lateinit var binding: FragmentPropertyDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPropertyDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.propertyDetail.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { propertyDetail ->
                        Log.d("XXX", "SUCCESS LOADING DETAIL: " + propertyDetail)
                        setupPropertyImages(propertyDetail)
                        setupPropertyDetails(propertyDetail)
                    }
                }

                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.d("XXX", "ERROR LOADING: " + message)
                    }
                }

                is Resource.Loading -> {
                    Log.d("XXX", "LOADING IN PROGRESS")
                }
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
    private fun setupPropertyImages(propertyDetail: PropertyDetail) {
        val imageList = propertyDetail.multimedia.images.map { it.url }
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val pagerState = rememberPagerState(pageCount = { imageList.size })
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) { page ->
                        Box {
                            GlideImage(
                                model = imageList[page],
                                contentDescription = "Image $page",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                    Row(
                        Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(pagerState.pageCount) { iteration ->
                            val color = if (pagerState.currentPage == iteration) {
                                Color.DarkGray.copy(alpha = 0.7f)
                            } else {
                                Color.LightGray.copy(alpha = 0.5f)
                            }
                            Box(
                                modifier = Modifier
                                    .padding(2.dp)
                                    .clip(CircleShape)
                                    .background(color)
                                    .size(8.dp)
                            )
                        }
                    }
                }
            }
        }
    }

    private fun setupPropertyDetails(propertyDetail: PropertyDetail) {
        binding.apply {
            propertyPrice.text = context?.getString(
                R.string.property_price_text,
                propertyDetail.priceInfo.amount.toInt(),
                propertyDetail.priceInfo.currencySuffix
            )
            propertyRooms.text = context?.getString(
                R.string.property_rooms_text,
                propertyDetail.moreCharacteristics.roomNumber
            )
            propertyBathrooms.text = context?.getString(
                R.string.property_bathrooms_text,
                propertyDetail.moreCharacteristics.bathNumber
            )
            propertySize.text = context?.getString(
                R.string.property_size_text,
                propertyDetail.moreCharacteristics.constructedArea
            )
            propertyFloor.text = context?.getString(
                R.string.property_floor_text,
                propertyDetail.moreCharacteristics.floor
            )
            properyDescription.text = propertyDetail.propertyComment
        }
    }
}