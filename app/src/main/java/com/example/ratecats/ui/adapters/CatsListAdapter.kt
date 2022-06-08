package com.example.ratecats.ui.adapters

import android.util.Log
import android.view.LayoutInflater
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
    ) : ListAdapter<LocalFavoritedImg, CatsListAdapter.CatsViewHolder>(CatPhotoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        return CatsViewHolder.from(catsVm, parent)
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) =
        holder.bind(getItem(position))

    class CatsViewHolder private constructor(
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
                executePendingBindings()
                favoriteImgBtn.setOnClickListener {
                    // todo: change the image to on (or off and undo favorite)
                    //  check if imgBtn is on or off
                    catsVm.addFavorite(catPhoto.imgId)
//                    catsVm.getAllMyFavorites()
                    Log.d(TAG, "id sent: ${catPhoto.imgId}")

                    // todo: remove favorite when appropriate
//                    catsVm.removeFavorite(catPhoto.id)
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

    class CatPhotoDiffCallback : DiffUtil.ItemCallback<LocalFavoritedImg>() {
        override fun areItemsTheSame(oldItem: LocalFavoritedImg, newItem: LocalFavoritedImg): Boolean {
            return oldItem.imgId == newItem.imgId
        }
        override fun areContentsTheSame(oldItem: LocalFavoritedImg, newItem: LocalFavoritedImg): Boolean {
            return oldItem == newItem
        }
    }
}