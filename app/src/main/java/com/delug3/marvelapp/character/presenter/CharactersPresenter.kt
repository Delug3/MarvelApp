package com.delug3.marvelapp.character.presenter

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.delug3.marvelapp.character.model.MarvelResponse
import com.delug3.marvelapp.character.model.ResultsItem
import com.delug3.marvelapp.character.repository.CharacterApiService
import com.delug3.marvelapp.character.view.Characters
import com.delug3.marvelapp.character.view.CharactersMainActivity
import com.delug3.marvelapp.common.network.RetrofitClient.getClientPublic
import com.delug3.marvelapp.common.utilities.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class CharactersPresenter(charactersMainActivity: CharactersMainActivity) : Characters.Presenter {

    var charactersList: List<ResultsItem>? = null
    var list: ArrayList<ResultsItem>? = null
    override fun getCharacters() {
        val service = getClientPublic?.create(CharacterApiService::class.java)
        val call =
            service?.getEpisodes(Constants.timeStamp, Constants.PUBLIC_KEY, Constants.hashKey())
        call!!.enqueue(object : Callback<MarvelResponse?> {
            override fun onResponse(
                call: Call<MarvelResponse?>,
                response: Response<MarvelResponse?>
            ) {
                if (response.isSuccessful) {

                    val result = response.body()?.data?.results

                } else {
                    Log.e(TAG, "onResponse: " + response.errorBody())
                }
            }

            override fun onFailure(call: Call<MarvelResponse?>, t: Throwable) {
                Log.e(TAG, "onFailure" + t.message)

            }
        })
    }

    companion object {
        private const val TAG = "CHARACTERS"
    }

}