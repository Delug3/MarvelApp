package com.delug3.marvelapp.character.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle

import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.delug3.marvelapp.character.adapter.CharactersListAdapter
import com.delug3.marvelapp.character.model.ResultsItem
import com.delug3.marvelapp.character.viewmodels.CharactersViewModel
import com.delug3.marvelapp.common.utilities.CommonUtils
import com.delug3.marvelapp.common.utilities.Constants
import com.delug3.marvelapp.databinding.ActivityMainCharactersBinding
import androidx.recyclerview.widget.RecyclerView

import com.google.android.material.snackbar.Snackbar


class CharactersMainActivity : AppCompatActivity()  {
    private lateinit var binding: ActivityMainCharactersBinding
    private var charactersListAdapter: CharactersListAdapter? = null
    private var charactersList: ArrayList<ResultsItem?> = ArrayList()
    private val charactersViewModel: CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        setUpSwipeRefresh()
        checkConnectivity()
    }


    /**This method set up the swipe refresh in case we need to refresh the characters list
     *
     */
    private fun setUpSwipeRefresh() {
        binding.swipeRefreshItems.setOnRefreshListener {
            sendDataToAdapter()
            binding.swipeRefreshItems.isRefreshing = false
        }
    }


    /**This method set up the recyclerview
     * Changes to decoration and layout must be done here
     */
    private fun setUpRecyclerView() {
        charactersListAdapter = CharactersListAdapter(this, charactersList)
        binding.recyclerViewCharacters.adapter = charactersListAdapter
        binding.recyclerViewCharacters.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCharacters.layoutManager = layoutManager
        binding.recyclerViewCharacters.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerViewCharacters.context,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.recyclerViewCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)){
                    showCustomSnackBar()
                    updateOffSet()
                    sendDataToAdapter()
                }
            }

        })
    }

    /**This method shows a custom snackBar when we need to obtain more characters
     *
     */
    private fun showCustomSnackBar() {
        val snackBar = Snackbar.make(binding.constraintLayout, Constants.LOADING, Snackbar.LENGTH_SHORT)
            val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(Color.BLACK)
            val textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
            textView.setTextColor(Color.RED)
            textView.textSize = 20f
        snackBar.show()
    }


    /**This method increase the offset for pagination purposes
     *
     */
    private fun updateOffSet() {
        charactersViewModel.updateOffset()
    }


    /**This method checks the connectivity,
     * if internet is available then the data is sent to the adapter
     */
    private fun checkConnectivity() {
        if (CommonUtils.isInternetAvailable(this)) {
            sendDataToAdapter()
        } else {
            Toast.makeText(applicationContext, Constants.NO_CONNECTION, Toast.LENGTH_SHORT).show()
        }
    }


    /**This method observe a viewModel value, when the value changes, triggers this method
     *A message is shown in case that no data is available to be sent to the adapter
     */
    private fun sendDataToAdapter() {
        charactersViewModel.fetchCharacters().observe(this, { characters ->
            if (characters == null) {
                Toast.makeText(applicationContext, Constants.NO_DATA, Toast.LENGTH_SHORT).show()
            } else {
                characters.let { charactersListAdapter?.setCharacters(it) }
            }
        })
    }


    /**This method open a new activity sending the character ID based in the current position
     * @param position: Int  receive the position in the recyclerView list
     */
    fun onCharacterItemClick(position: Int) {
        val characterId: Int? = charactersList[position]?.id
        openCharacterActivityDetails(characterId)
    }


    /**This method open a new activity
     * @param characterId: Int? receive the id so we can obtain more data in the next screen
     */
    private fun openCharacterActivityDetails(characterId: Int?) {
        val intent = Intent(this, CharacterDetailActivity::class.java)
        intent.putExtra(Constants.CHARACTER_ID, characterId)
        startActivity(intent)
    }
}