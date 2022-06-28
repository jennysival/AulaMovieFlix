package br.com.zup.movieflix.moviedetail.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import br.com.zup.movieflix.CHAVE_MOVIE
import br.com.zup.movieflix.databinding.ActivityMovieDetailBinding
import br.com.zup.movieflix.home.model.Movie
import br.com.zup.movieflix.moviedetail.viewModel.MovieDetailViewModel

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMovieDetailBinding
    private val viewModel : MovieDetailViewModel by lazy {
        ViewModelProvider(this)[MovieDetailViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observable()
        getPassedData()
    }
    fun getPassedData(){
        val movie = intent.getParcelableExtra<Movie>(CHAVE_MOVIE)
        movie?.let { viewModel.getMovieWithDirector(it) }
    }
    fun observable(){
        viewModel.response.observe(this){
            //todo setar campos
            binding.tvMovieTitle.text = it.movie.title
            binding.tvDirectorName.text = it.director.name
        }
    }
}