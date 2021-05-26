package id.trials.moviecatalog

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.trials.moviecatalog.models.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class AdapterMovie(
    private val movies: List<Movie>
) : RecyclerView.Adapter<AdapterMovie.MovieViewHolder>() {

    private lateinit var context: Context

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgBaseURL = "https://image.tmdb.org/t/p/w500/"
        fun bindMovie(movie: Movie, context: Context) {
            itemView.apply {
                tvTitle.text = movie.title
                tvReleaseDate.text = ConvertDate.toDate(movie.release)
                Glide.with(this).load(imgBaseURL + movie.poster)
                    .into(this.ivPosterPicture)
            }
            itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("poster", imgBaseURL + movie.poster)
                intent.putExtra("id_movie", movie.id)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        context = parent.context
        return MovieViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_movie, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies[position], context)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}