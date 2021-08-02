package com.delug3.marvelapp.character.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.delug3.marvelapp.character.model.ResultsItem
import com.delug3.marvelapp.databinding.ItemsCharactersBinding
import java.util.*

class CharactersListAdapter(private val charactersList: MutableList<ResultsItem>) :
    RecyclerView.Adapter<CharactersListAdapter.ViewHolder?>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemsCharactersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resultsItem = charactersList[position]
        holder.bind(resultsItem)
        //holder.itemView.setOnClickListener { charactersActivity.onCharacterItemClick(position) }
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }

    fun addCharactersList(charactersList: ArrayList<ResultsItem>?) {
        charactersList?.addAll(charactersList)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemsCharactersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(resultsItem: ResultsItem) {
            binding.tvName.text = resultsItem.name
            binding.tvDescription.text = resultsItem.description
        }
    }

}



