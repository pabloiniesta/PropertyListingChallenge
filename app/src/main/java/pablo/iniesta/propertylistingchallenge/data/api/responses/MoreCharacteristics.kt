package pablo.iniesta.propertylistingchallenge.data.api.responses

data class MoreCharacteristics(
    val agencyIsABank: Boolean,
    val bathNumber: Int,
    val boxroom: Boolean,
    val communityCosts: Double,
    val constructedArea: Int,
    val energyCertificationType: String,
    val exterior: Boolean,
    val flatLocation: String,
    val floor: String,
    val housingFurnitures: String,
    val isDuplex: Boolean,
    val lift: Boolean,
    val modificationDate: Long,
    val roomNumber: Int,
    val status: String
)