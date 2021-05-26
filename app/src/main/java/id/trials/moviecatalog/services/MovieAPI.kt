package id.trials.moviecatalog.services

import id.trials.moviecatalog.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieAPI {

    @GET("/3/movie/{category}?api_key=bbf5a3000e95f1dddf266b5e187d4b21")
    fun getMovieList(@Path("category") category: String): Call<MovieResponse>

}