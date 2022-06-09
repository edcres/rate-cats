package com.example.ratecats.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
    private val catsVm: CatsViewModel,
    private val context: Context
    ) : ListAdapter<CatPhoto, CatsListAdapter.CatsViewHolder>(CatPhotoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        return CatsViewHolder.from(catsVm, context, parent)
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) =
        holder.bind(getItem(position))

    class CatsViewHolder private constructor(
        private val catsVm: CatsViewModel,
        private val context: Context,
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
                executePendingBindings()
                favoriteImgBtn.setOnClickListener {
                    when(favoriteImgBtn.drawable) {
                        ContextCompat.getDrawable(context, R.drawable.ic_favorite_off_24) -> {
                            catsVm.addFavorite(LocalFavoritedImg(catPhoto.id, catPhoto.imgSrcUrl))
                            favoriteImgBtn.setImageResource(R.drawable.ic_favorite_on_24)
                        }
                        ContextCompat.getDrawable(context, R.drawable.ic_favorite_on_24) -> {
                            catsVm.removeFavorite(LocalFavoritedImg(catPhoto.id, catPhoto.imgSrcUrl))
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
            ): CatsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CatPhotoItemBinding
                    .inflate(layoutInflater, parent, false)
                return CatsViewHolder(catsVm, context, binding)
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