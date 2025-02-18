package pablo.iniesta.propertylistingchallenge.presentation.propertylist.adapters

sealed class PropertyListItemChangePayload {
    data object FavoriteChanged : PropertyListItemChangePayload()
}