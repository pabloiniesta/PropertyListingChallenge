package pablo.iniesta.propertylistingchallenge.presentation.propertylist.adapters

import pablo.iniesta.propertylistingchallenge.data.db.PropertyEntity

interface PropertyListItemListener {
    fun onItemClick(property: PropertyEntity)
    fun onFavoriteClick(property: PropertyEntity, isFav: Boolean)
}