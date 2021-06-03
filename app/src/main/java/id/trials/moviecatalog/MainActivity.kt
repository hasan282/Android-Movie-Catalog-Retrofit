package id.trials.moviecatalog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.trials.moviecatalog.models.Movie
import id.trials.moviecatalog.models.MovieResponse
import id.trials.moviecatalog.services.MovieAPI
import id.trials.moviecatalog.services.MovieService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var category: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCategory.text = intent.getStringExtra("category")
        category = intent.getStringExtra("url").toString()

        rvMovieList.layoutManager = LinearLayoutManager(this)
        rvMovieList.setHasFixedSize(true)

        srlRefresh.setOnRefreshListener {
            load()
            srlRefresh.isRefreshing = false
        }

        load()
    }

    private fun load() {
        getMovieData { movies: List<Movie> ->
            rvMovieList.adapter = AdapterMovie(movies)
        }
    }

    private fun getMovieData(callback: (List<Movie>) -> Unit) {
        val apiService = MovieService.getInstance().create(MovieAPI::class.java)
        apiService.getMovieList(category).enqueue(object : Callback<MovieResponse> {

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }
        })
    }
}