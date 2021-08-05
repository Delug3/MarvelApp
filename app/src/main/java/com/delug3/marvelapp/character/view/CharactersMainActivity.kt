package com.delug3.marvelapp.character.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.delug3.marvelapp.character.adapter.CharactersListAdapter
import com.delug3.marvelapp.character.model.ResultsItem
import com.delug3.marvelapp.character.viewmodels.CharactersViewModel
import com.delug3.marvelapp.common.utilities.CommonUtils
import com.delug3.marvelapp.common.utilities.Constants
import com.delug3.marvelapp.databinding.ActivityMainCharactersBinding


class CharactersMainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainCharactersBinding
    private var charactersListAdapter: CharactersListAdapter? = null
    private var charactersList: ArrayList<ResultsItem?> = ArrayList()
    private val charactersViewModel: CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        checkConnectivity()
    }


    private fun setUpRecyclerView() {
        charactersListAdapter = CharactersListAdapter(this, charactersList)
        binding.recyclerViewCharacters.adapter = charactersListAdapter
        binding.recyclerViewCharacters.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCharacters.layoutManager = layoutManager
        binding.recyclerViewCharacters.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerViewCharacters.getContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun checkConnectivity() {
        if (CommonUtils.isInternetAvailable(this)) {
            sendDataToAdapter()
        } else {
            Toast.makeText(applicationContext, Constants.NO_CONNECTION, Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendDataToAdapter() {
        charactersViewModel.fetchCharacters()?.observe(this, { characters ->
            characters?.let { charactersListAdapter?.setCharacters(it) }
        })
    }

    fun onCharacterItemClick(position: Int) {
        val characterId: Int? = charactersList[position]?.id
        openCharacterActivityDetails(characterId)
    }

    private fun openCharacterActivityDetails(characterId: Int?) {
        val intent = Intent(this, CharacterDetailActivity::class.java)
        intent.putExtra(Constants.CHARACTER_ID, characterId)
        startActivity(intent)
    }
}