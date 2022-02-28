package com.andikajayaw.movietrailerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andikajayaw.movietrailerapp.R
import com.andikajayaw.movietrailerapp.model.MovieModel
import kotlinx.android.synthetic.main.adapter_main.view.*

class MainAdapter(var movies: ArrayList<MovieModel>): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_main, parent, false)
    )

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = movies.size

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
//        val view = view
        var textTitle: TextView = view.findViewById(R.id.textTitle)
        fun bind(movies: MovieModel) {
            textTitle.text = movies.title
        }
    }

    public fun setData(newMovies: List<MovieModel>) {
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

}