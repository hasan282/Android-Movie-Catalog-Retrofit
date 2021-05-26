package id.trials.moviecatalog

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        context = this

        btnPopular.setOnClickListener { toListMovie("Popular Movie", "popular") }
        btnTopRated.setOnClickListener { toListMovie("Top Rated Movie", "top_rated") }
        btnUpcoming.setOnClickListener { toListMovie("Upcoming Movie", "upcoming") }
    }

    private fun toListMovie(category: String, url: String) {
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra("category", category)
        intent.putExtra("url", url)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}