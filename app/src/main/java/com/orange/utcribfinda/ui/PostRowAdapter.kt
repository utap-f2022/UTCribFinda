package com.orange.utcribfinda.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.orange.utcribfinda.MainActivity
import com.orange.utcribfinda.R
import com.orange.utcribfinda.ui.saved.SavedViewModel
import com.orange.utcribfinda.api.ListingPost
import com.orange.utcribfinda.databinding.ListingPostBinding


class PostRowAdapter(private val viewModel: SavedViewModel)
    : ListAdapter<ListingPost, PostRowAdapter.VH>(ListingDiff()) {

    class ListingDiff : DiffUtil.ItemCallback<ListingPost>() {
        override fun areItemsTheSame(oldItem: ListingPost, newItem: ListingPost): Boolean {
            //return false
            return oldItem.floorPlanName == newItem.floorPlanName
        }
        override fun areContentsTheSame(oldItem: ListingPost, newItem: ListingPost): Boolean {
            return oldItem.floorPlanName == newItem.floorPlanName
            //return false
//            return ListingPost.spannableStringsEqual(oldItem.title, newItem.title) &&
//                    ListingPost.spannableStringsEqual(oldItem.selfText, newItem.selfText) &&
//                    ListingPost.spannableStringsEqual(oldItem.publicDescription, newItem.publicDescription) &&
//                    ListingPost.spannableStringsEqual(oldItem.displayName, newItem.displayName)

        }
    }

    inner class VH(val rowPostBinding: ListingPostBinding)
        : RecyclerView.ViewHolder(rowPostBinding.root) {

        init {
            rowPostBinding.rowFav.setOnClickListener {
                val item = getItem(adapterPosition)
                if(viewModel.observeSavedList().contains(item)) {
                    viewModel.removeSaved(item)
                    rowPostBinding.rowFav.setImageResource(R.drawable.ic_favorite_border_black_24dp)
                } else {
                    viewModel.setSavedList(item)
                    rowPostBinding.rowFav.setImageResource(R.drawable.ic_favorite_black_24dp)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ListingPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        val rowBinding = holder.rowPostBinding

        rowBinding.apartmentComplex.text = "Villas On Rio"
        rowBinding.floorPlan.text = item.floorPlanName
        rowBinding.beds.text = item.numBeds.toString()
        rowBinding.baths.text = item.numBaths.toString()
        rowBinding.sqFt.text = item.sqFT.toString() + " sq ft."
        rowBinding.price.text = "$" + item.price.toString() + " /mo."

        // if heart was clicked change image
        if(viewModel.observeSavedList().contains(item)) {
            rowBinding.rowFav.setImageResource(R.drawable.ic_favorite_black_24dp)
        } else {
            rowBinding.rowFav.setImageResource(R.drawable.ic_favorite_border_black_24dp)
        }

    }
}