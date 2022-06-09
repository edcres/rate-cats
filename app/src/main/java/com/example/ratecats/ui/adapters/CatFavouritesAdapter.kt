package com.example.ratecats.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
    private val catsVm: CatsViewModel,
    private val context: Context
) : ListAdapter<LocalFavoritedImg, CatFavouritesAdapter.LocalCatsViewHolder>(LocalCatDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalCatsViewHolder =
        LocalCatsViewHolder.from(catsVm, context, parent)

    override fun onBindViewHolder(holder: LocalCatsViewHolder, position: Int) =
        holder.bind(getItem(position))

    class LocalCatsViewHolder private constructor(
        private val catsVm: CatsViewModel,
        private val context: Context,
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
                executePendingBindings()
                favoriteImgBtn.setOnClickListener {
                    when(favoriteImgBtn.drawable) {
                        ContextCompat.getDrawable(context, R.drawable.ic_favorite_off_24) -> {
                            catsVm.addFavorite(LocalFavoritedImg(catPhoto.imgId, catPhoto.imgUrl))
                            favoriteImgBtn.setImageResource(R.drawable.ic_favorite_on_24)
                        }
                        ContextCompat.getDrawable(context, R.drawable.ic_favorite_on_24) -> {
                            catsVm.removeFavorite(LocalFavoritedImg(catPhoto.imgId, catPhoto.imgUrl))
                            favoriteImgBtn.setImageResource(R.drawable.ic_favorite_off_24)
                        }
                    }
                }
            }
        }

        companion object {
            fun from(
                catsVm: CatsViewModel,
                context: Context,
                parent: ViewGroup
            ): LocalCatsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CatPhotoItemBinding
                    .inflate(layoutInflater, parent, false)
                return LocalCatsViewHolder(catsVm, context, binding)
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