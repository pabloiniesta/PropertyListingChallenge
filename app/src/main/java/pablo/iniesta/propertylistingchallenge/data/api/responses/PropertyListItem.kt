package pablo.iniesta.propertylistingchallenge.data.api.responses

data class PropertyListItem(
    val address: String,
    val bathrooms: Int,
    val country: String,
    val description: String,
    val district: String,
    val exterior: Boolean,
    val features: Features,
    val floor: String,
    val latitude: Double,
    val longitude: Double,
    val multimedia: Multimedia,
    val municipality: String,
    val neighborhood: String,
    val operation: String,
    val parkingSpace: ParkingSpace,
    val price: Double,
    val priceInfo: PriceInfo,
    val propertyCode: String,
    val propertyType: String,
    val province: String,
    val rooms: Int,
    val size: Double,
    val thumbnail: String
)