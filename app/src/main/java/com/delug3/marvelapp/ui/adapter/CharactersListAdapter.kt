package com.delug3.marvelapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.delug3.marvelapp.data.model.ResultsItem
import com.delug3.marvelapp.data.model.ResultsWithUrls
import com.delug3.marvelapp.ui.view.CharactersMainActivity
import com.delug3.marvelapp.utils.Constants
import com.delug3.marvelapp.databinding.ItemsCharactersBinding
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList

class CharactersListAdapter(
    private val charactersMainActivity: CharactersMainActivity,
    private var charactersList: ArrayList<ResultsItem?>
) :
    RecyclerView.Adapter<CharactersListAdapter.ViewHolder?>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemsCharactersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resultsItem = charactersList[position]
        if (resultsItem != null) {
            holder.bind(resultsItem)
        }
        holder.itemView.setOnClickListener { charactersMainActivity.onCharacterItemClick(position) }
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCharacters(characters: List<ResultsItem?>) {
        characters.let { charactersList.addAll(it) }
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemsCharactersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(resultsItem: ResultsItem) {
            //checking if the name is empty, in that case we add a value from Constants
            if (resultsItem.name == "") {
                binding.tvName.text = Constants.NO_NAME
            } else {
                binding.tvName.text = resultsItem.name
            }
            val thumbnail = "${resultsItem.thumbnail?.path}.${resultsItem.thumbnail?.extension}"
            Picasso.get().load(thumbnail).into(binding.ivCharacterImage)
        }
    }

}



