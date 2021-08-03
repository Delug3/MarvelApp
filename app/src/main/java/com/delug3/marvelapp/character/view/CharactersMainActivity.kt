package com.delug3.marvelapp.character.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.delug3.marvelapp.character.adapter.CharactersListAdapter
import com.delug3.marvelapp.character.model.ResultsItem
import com.delug3.marvelapp.character.presenter.CharactersPresenter
import com.delug3.marvelapp.databinding.ActivityMainCharactersBinding


class CharactersMainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainCharactersBinding
    private var charactersListAdapter: CharactersListAdapter? = null
    var charactersList: MutableList<ResultsItem?> = ArrayList()

    private var charactersPresenter: CharactersPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        charactersPresenter = CharactersPresenter(this)

        setUpRecyclerView()

        getData()
    }


    private fun setUpRecyclerView() {
        /*charactersListAdapter = CharactersListAdapter(this, charactersList)
        binding.recyclerViewPosts.adapter = postsAdapter
        binding.recyclerViewPosts.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewPosts.layoutManager = layoutManager

        binding.recyclerViewPosts.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerViewPosts.getContext(),
                DividerItemDecoration.VERTICAL
            )
        )*/
    }

    private fun getData() {
        val connection = isInternetAvailable(this)
        if (connection) {
            sendDataToRecyclerView()
        } else {
            //error
        }
    }

    private fun sendDataToRecyclerView() {
        charactersPresenter?.getCharacters()
    }

    private fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }


}