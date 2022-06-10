package com.example.ratecats.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.ratecats.R
import com.example.ratecats.data.room.LocalFavoritedImg
import com.example.ratecats.databinding.CatPhotoItemBinding
import com.example.ratecats.ui.viewmodels.CatsViewModel

private const val TAG = "LocalCatsAdapt__TAG"

class CatFavouritesAdapter(
    private val catsVm: CatsViewModel
) : ListAdapter<LocalFavoritedImg, CatFavouritesAdapter.LocalCatsViewHolder>(LocalCatDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalCatsViewHolder =
        LocalCatsViewHolder.from(catsVm, parent)

    override fun onBindViewHolder(holder: LocalCatsViewHolder, position: Int) =
        holder.bind(getItem(position))

    class LocalCatsViewHolder private constructor(
        private val catsVm: CatsViewModel,
        private val binding: CatPhotoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(catPhoto: LocalFavoritedImg) {
            binding.apply {
                Glide.with(catImg.context)
                    .load(catPhoto.imgUrl.toUri())
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.loading_anim)
                    )
                    .into(catImg)

                favoriteOffBtn.visibility = View.GONE
                favoriteOnBtn.visibility = View.VISIBLE

                favoriteOffBtn.setOnClickListener {
                    catsVm.addFavorite(LocalFavoritedImg(catPhoto.imgId, catPhoto.imgUrl))
                    switchBtnsVisibility()
                }
                favoriteOnBtn.setOnClickListener {
                    Log.d(TAG, "bind: clicked")
                    catsVm.removeFavorite(LocalFavoritedImg(catPhoto.imgId, catPhoto.imgUrl))
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
            ): LocalCatsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CatPhotoItemBinding
                    .inflate(layoutInflater, parent, false)
                return LocalCatsViewHolder(catsVm, binding)
            }
        }
    }

    class LocalCatDiffCallback : DiffUtil.ItemCallback<LocalFavoritedImg>() {
        override fun areItemsTheSame(oldItem: LocalFavoritedImg, newItem: LocalFavoritedImg): Boolean {
            return oldItem.imgId == newItem.imgId
        }
        override fun areContentsTheSame(oldItem: LocalFavoritedImg, newItem: LocalFavoritedImg): Boolean {
            return oldItem == newItem
        }
    }
}