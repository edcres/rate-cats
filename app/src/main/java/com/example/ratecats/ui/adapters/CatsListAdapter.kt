package com.example.ratecats.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.ratecats.R
import com.example.ratecats.data.catsapi.CatPhoto
import com.example.ratecats.data.room.LocalFavoritedImg
import com.example.ratecats.databinding.CatPhotoItemBinding
import com.example.ratecats.ui.viewmodels.CatsViewModel

private const val TAG = "CatsAdapt__TAG"

class CatsListAdapter(
    private val catsVm: CatsViewModel
    ) : ListAdapter<CatPhoto, CatsListAdapter.CatsViewHolder>(CatPhotoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        return CatsViewHolder.from(catsVm, parent)
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) =
        holder.bind(getItem(position))

    class CatsViewHolder private constructor(
        private val catsVm: CatsViewModel,
        private val binding: CatPhotoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(catPhoto: CatPhoto) {
            binding.apply {
                
                Glide.with(catImg.context)
                    .load(catPhoto.imgSrcUrl.toUri())
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.loading_anim)
                    )
                    .into(catImg)

                if(catsVm.favoritesContainsId(catPhoto.id)) {
                    favoriteOffBtn.visibility = View.GONE
                    favoriteOnBtn.visibility = View.VISIBLE
                }

                favoriteOffBtn.setOnClickListener {
                    catsVm.addFavorite(LocalFavoritedImg(
                        catPhoto.id, null, null, catPhoto.imgSrcUrl)
                    )
                    switchBtnsVisibility()
                }
                favoriteOnBtn.setOnClickListener {
                    catsVm.removeFavorite(LocalFavoritedImg(
                        catPhoto.id, null, null, catPhoto.imgSrcUrl)
                    )
                    switchBtnsVisibility()
                }
                executePendingBindings()
            }
        }

        private fun switchBtnsVisibility() {
            binding.apply {
                if(favoriteOffBtn.visibility == View.VISIBLE) {
                    favoriteOffBtn.visibility = View.GONE
                    favoriteOnBtn.visibility = View.VISIBLE
                } else {
                    favoriteOffBtn.visibility = View.VISIBLE
                    favoriteOnBtn.visibility = View.GONE
                }
            }
        }

        companion object {
            fun from(
                catsVm: CatsViewModel,
                parent: ViewGroup
            ): CatsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CatPhotoItemBinding
                    .inflate(layoutInflater, parent, false)
                return CatsViewHolder(catsVm, binding)
            }
        }
    }

    class CatPhotoDiffCallback : DiffUtil.ItemCallback<CatPhoto>() {
        override fun areItemsTheSame(oldItem: CatPhoto, newItem: CatPhoto): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: CatPhoto, newItem: CatPhoto): Boolean {
            return oldItem == newItem
        }
    }
}