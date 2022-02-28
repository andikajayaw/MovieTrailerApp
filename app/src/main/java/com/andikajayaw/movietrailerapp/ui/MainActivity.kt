package com.andikajayaw.movietrailerapp.ui

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andikajayaw.movietrailerapp.R
import com.andikajayaw.movietrailerapp.adapter.MainAdapter
import com.andikajayaw.movietrailerapp.databinding.ActivityMainBinding
import com.andikajayaw.movietrailerapp.model.Constant
import com.andikajayaw.movietrailerapp.model.MovieResponse
import com.andikajayaw.movietrailerapp.retrofit.ApiService
//import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    lateinit var mainAdapter: MainAdapter

    companion object {
        private const val TAG: String = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        setupView()
        setupRecyclerView()

//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//

    }

    override fun onStart() {
        super.onStart()
        getMovie()
    }

    private fun setupView() {
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun setupRecyclerView() {
        mainAdapter = MainAdapter(arrayListOf())
        recyclerView = findViewById(R.id.recyclerViewMovie)
        recyclerView.setHasFixedSize(true)
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = mainAdapter
        }
    }

    private fun getMovie() {
        showLoading(true)
        ApiService().endpoint.getMovieNowPlaying(Constant.API_KEY,1)
            .enqueue(object : Callback<MovieResponse>{
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    showLoading(false)
                    if(response.isSuccessful) {
                        showMovie(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    showLoading(false)
                    Log.e(TAG, "errorResponse: $t")
                }

            })
    }

    fun showLoading(loading: Boolean) {
        progressBar = findViewById(R.id.progressBar)
        when(loading) {
            true -> progressBar.visibility = View.VISIBLE
            false -> progressBar.visibility = View.GONE
        }
    }

    fun showMovie(response: MovieResponse) {
//        Log.d(TAG, "responseMovie: $response")
//        Log.d(TAG, "responseTotalPage: ${response.total_pages}")
//
//        for(movie in response.results) {
//            Log.d(TAG, "title: ${movie.title}")
//        }
        mainAdapter.setData(response.results)
    }

    fun showMessage(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}