package pablo.iniesta.propertylistingchallenge.data.api.responses

data class PropertyDetail(
    val adid: Int,
    val country: String,
    val energyCertification: EnergyCertification,
    val extendedPropertyType: String,
    val homeType: String,
    val moreCharacteristics: MoreCharacteristics,
    val multimedia: PropertyMultimedia,
    val operation: String,
    val price: Double,
    val priceInfo: PriceInfoX,
    val propertyComment: String,
    val propertyType: String,
    val state: String,
    val ubication: Ubication
)