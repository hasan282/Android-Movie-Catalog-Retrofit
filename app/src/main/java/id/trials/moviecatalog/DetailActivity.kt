package id.trials.moviecatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import id.trials.moviecatalog.models.DetailResponse
import id.trials.moviecatalog.services.MovieDetail
import id.trials.moviecatalog.services.MovieService
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setDetailMovie(
            intent.getStringExtra("id_movie").toString(),
            this
        )
    }

    private fun setDetailMovie(id: String, activity: AppCompatActivity) {
        val detailService = MovieService.getInstance().create(MovieDetail::class.java)
        detailService.getDetail(id).enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                tvOriginalTitle.text = response.body()!!.title
                tvReleaseDate.text = ConvertDate.toDate(response.body()!!.release)
                tvOverview.text = response.body()!!.overview

                Glide.with(activity)
                    .load("https://image.tmdb.org/t/p/w500/" + response.body()!!.poster)
                    .into(ivPosterDetail)
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
            }
        })
    }


}