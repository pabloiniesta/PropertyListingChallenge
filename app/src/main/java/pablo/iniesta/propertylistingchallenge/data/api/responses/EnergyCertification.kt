package pablo.iniesta.propertylistingchallenge.data.api.responses

data class EnergyCertification(
    val emissions: Emissions,
    val energyConsumption: EnergyConsumption,
    val title: String
)