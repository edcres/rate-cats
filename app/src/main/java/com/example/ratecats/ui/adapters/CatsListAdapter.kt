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
                executePendingBindings()
                favoriteImgBtn.setOnClickListener {
                    // todo: change the image to on (or off and undo favorite)
                    //  check if imgBtn is on or off
                    catsVm.addFavorite(catPhoto.id)
//                    catsVm.getAllMyFavorites()
                    Log.d(TAG, "id sent: ${catPhoto.id}")

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

    class CatPhotoDiffCallback : DiffUtil.ItemCallback<CatPhoto>() {
        override fun areItemsTheSame(oldItem: CatPhoto, newItem: CatPhoto): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
        override fun areContentsTheSame(oldItem: CatPhoto, newItem: CatPhoto): Boolean {
            return oldItem == newItem
        }
    }
}