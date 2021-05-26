package id.trials.moviecatalog.services

import id.trials.moviecatalog.models.DetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetail {

    @GET("/3/movie/{id_movie}?api_key=bbf5a3000e95f1dddf266b5e187d4b21")
    fun getDetail(@Path("id_movie") id: String): Call<DetailResponse>

}