package com.andikajayaw.movietrailerapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.andikajayaw.movietrailerapp.R
import com.andikajayaw.movietrailerapp.model.Constant
import com.andikajayaw.movietrailerapp.model.trailer.TrailerResponse
import com.andikajayaw.movietrailerapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class TrailerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trailer)
        setupView()
        setupListener()
    }

    override fun onStart() {
        super.onStart()
        getMovieTrailer()
    }

    private fun setupView() {

    }

    private fun setupListener() {

    }

    private fun getMovieTrailer() {
        showLoading(true)
        ApiService().endpoint.getMovieTrailer(Constant.MOVIE_ID, Constant.API_KEY)
            .enqueue(object : Callback<TrailerResponse> {
                override fun onResponse(
                    call: Call<TrailerResponse>,
                    response: Response<TrailerResponse>
                ) {
                    showLoading(false)
                    if(response.isSuccessful) {
                        showTrailer(response.body()!!)
                    }

                }

                override fun onFailure(call: Call<TrailerResponse>, t: Throwable) {
                    showLoading(false)
                }

            })
    }

    private fun showLoading(loading: Boolean) {
        when(loading) {
            true -> {

            }
            else -> {

            }
        }
    }

    private fun showTrailer(trailers: TrailerResponse) {
        for (res in trailers.results) {
            Log.d(TAG, "trailerName: ${res.name}")
        }
    }

    companion object {
        private const val TAG: String = "TrailerActivity"
    }

}