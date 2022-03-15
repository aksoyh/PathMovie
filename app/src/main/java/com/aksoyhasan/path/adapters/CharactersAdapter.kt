package com.aksoyhasan.path.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aksoyhasan.path.R
import com.aksoyhasan.path.data.dto.CharactersResponse
import com.aksoyhasan.path.utils.internal.extension.emptyIfNull
import com.aksoyhasan.path.utils.internal.extension.zeroIfNull
import com.aksoyhasan.path.utils.loadImage
import kotlinx.android.synthetic.main.item_character_list.view.*

class CharactersAdapter :
    RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {

    class CharactersViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private val differCallback = object : DiffUtil.ItemCallback<CharactersResponse.Result>() {
        override fun areItemsTheSame(oldItem: CharactersResponse.Result, newItem: CharactersResponse.Result): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CharactersResponse.Result, newItem: CharactersResponse.Result): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharactersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_character_list, parent, false)
        return CharactersViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.itemView.apply {
            val imageUrl = "${item.thumbnail?.path.emptyIfNull()}.${item.thumbnail?.extension.emptyIfNull()}"
            ivCharacterPhoto.loadImage(imageUrl)
            tvCharacterName.text = item.name

            setOnClickListener {
                onItemClickListener?.invoke(item.id.zeroIfNull())
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }
}