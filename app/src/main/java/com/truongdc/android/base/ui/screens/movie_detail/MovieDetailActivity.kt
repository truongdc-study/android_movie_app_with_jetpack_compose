package com.truongdc.android.base.ui.screens.movie_detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.truongdc.android.base.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {
    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val movieId = intent.extras?.getInt("MOVIE_ID")
        movieId?.let { id ->
            supportFragmentManager.beginTransaction().apply {
                add(R.id.appContainerView, MovieDetailFragment.newInstance(id))
                commit()
            }
        }
    }
}
