package com.delug3.marvelapp.common.network

import com.delug3.marvelapp.common.utilities.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private var retrofit: Retrofit? = null

    /**
     * this method returns retrofit client instance
     * @return Retrofit object
     */
    @JvmStatic
    val getClientPublic: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }
            return retrofit
        }
}
