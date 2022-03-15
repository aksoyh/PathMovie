package com.aksoyhasan.path.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aksoyhasan.path.R
import com.aksoyhasan.path.data.dto.CharacterComicsResponse
import com.aksoyhasan.path.utils.internal.extension.emptyIfNull
import com.aksoyhasan.path.utils.loadImage
import kotlinx.android.synthetic.main.item_comics_list.view.*

class ComicsAdapter :
    RecyclerView.Adapter<ComicsAdapter.ComicsViewHolder>() {

    class ComicsViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private val differCallback = object : DiffUtil.ItemCallback<CharacterComicsResponse.Result>() {
        override fun areItemsTheSame(oldItem: CharacterComicsResponse.Result, newItem: CharacterComicsResponse.Result): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CharacterComicsResponse.Result, newItem: CharacterComicsResponse.Result): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComicsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_comics_list, parent, false)
        return ComicsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.itemView.apply {
            try {
                val imageUrl = "${item.thumbnail?.path.emptyIfNull()}.${item.thumbnail?.extension.emptyIfNull()}"
                tvComicsName.text = item.title
                ivComicsPhoto.loadImage(imageUrl)
            } catch (e: Exception) {
                Log.e("EXP", e.message.toString())
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}