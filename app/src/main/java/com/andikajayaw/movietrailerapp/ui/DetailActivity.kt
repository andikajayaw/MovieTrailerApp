package com.andikajayaw.movietrailerapp.ui

import android.os.Bundle
import android.util.Log
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.andikajayaw.movietrailerapp.R
import com.andikajayaw.movietrailerapp.databinding.ActivityDetailBinding
import com.andikajayaw.movietrailerapp.model.Constant
import com.andikajayaw.movietrailerapp.model.detailmovie.DetailResponse
import com.andikajayaw.movietrailerapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//import com.andikajayaw.movietrailerapp.ui.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        private const val TAG: String = "DetailActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onStart() {
        super.onStart()
        getMovieDetail()
    }

    private fun getMovieDetail() {
        ApiService().endpoint.getMovieDetail(Constant.MOVIE_ID, Constant.API_KEY).enqueue(object :
            Callback<DetailResponse>{
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                if(response.isSuccessful) {
                    showMovie(response.body()!!)
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.d(TAG, t.toString())
            }

        })
    }

    fun showMovie(detail: DetailResponse) {
        Log.d(TAG, "detailResponse: ${detail.overview}")
    }
}