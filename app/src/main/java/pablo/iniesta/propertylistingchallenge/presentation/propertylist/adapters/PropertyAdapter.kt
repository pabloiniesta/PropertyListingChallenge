package pablo.iniesta.propertylistingchallenge.presentation.propertylist.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pablo.iniesta.propertylistingchallenge.data.db.PropertyEntity
import pablo.iniesta.propertylistingchallenge.databinding.ItemPropertyBinding

class PropertyAdapter(private val listener: PropertyListItemListener) :
    RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder>() {

    inner class PropertyViewHolder(val binding: ItemPropertyBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<PropertyEntity>() {
        override fun areItemsTheSame(oldItem: PropertyEntity, newItem: PropertyEntity): Boolean {
            return oldItem.propertyCode == newItem.propertyCode
        }

        override fun areContentsTheSame(oldItem: PropertyEntity, newItem: PropertyEntity): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val binding =
            ItemPropertyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PropertyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val property = differ.currentList[position]
        holder.binding.apply {
            Glide.with(this.root).load(property.thumbnail).into(thumbnail)
            propertyAddress.text = "Piso en ${property.address}"
            propertyArea.text = "${property.district}, ${property.neighborhood}"
            propertyPrice.text =
                "${property.priceInfo.price.amount.toInt()} ${property.priceInfo.price.currencySuffix}"
            propertyRooms.text = "${property.rooms} hab."
            propertySize.text = "${property.size} m²"
            favoriteButton.isChecked = property.isFavorite

            root.setOnClickListener {
                listener.onItemClick(property)
            }

            favoriteButton.setOnCheckedChangeListener { _, _ ->
                listener.onFavoriteClick(property)
            }
        }
    }
}