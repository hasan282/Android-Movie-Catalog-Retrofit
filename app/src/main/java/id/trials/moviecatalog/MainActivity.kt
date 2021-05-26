package id.trials.moviecatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMovieList.layoutManager= LinearLayoutManager(this)
        rvMovieList.setHasFixedSize(true)
        getMovieData { movies: List<Movie> ->
            rvMovieList.adapter = AdapterMovie(movies)
        }
    }

    private fun getMovieData(callback: (List<Movie>) -> Unit) {
        val apiService = MovieService.getInstance().create(MovieAPI::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }
        })
    }
}