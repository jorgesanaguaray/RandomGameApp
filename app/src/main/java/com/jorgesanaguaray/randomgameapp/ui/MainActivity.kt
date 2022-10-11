package com.jorgesanaguaray.randomgameapp.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import coil.load
import com.jorgesanaguaray.randomgameapp.R
import com.jorgesanaguaray.randomgameapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Jorge Sanaguaray
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this).get()

        mainViewModel.gameItemModel.observe(this) {

            binding.apply {

                mImage.load(it.thumbnail) {
                    placeholder(R.drawable.ic_image)
                    error(R.drawable.ic_image)
                    crossfade(true)
                    crossfade(400)
                }
                mTitle.text = it.title
                mShortDescription.text = it.short_description

            }

            goToTheGamePage(it.game_url!!)

        }

        mainViewModel.message.observe(this) {
            binding.mTextViewMessage.text = it.toString()
        }

        mainViewModel.scrollViewVisibility.observe(this) {
            binding.mScrollView.visibility = if (it) View.VISIBLE else View.GONE
        }

        mainViewModel.textViewVisibility.observe(this) {
            binding.mTextViewMessage.visibility = if (it) View.VISIBLE else View.GONE
        }

        mainViewModel.progressBarVisibility.observe(this) {
            binding.mProgressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        binding.mFloatingButtonGetRandomGame.setOnClickListener {
            mainViewModel.getRandomGame()
        }

        mainViewModel.getRandomGame()

    }

    private fun goToTheGamePage(gameUrl: String) {

        binding.mButtonGoToTheGamePage.setOnClickListener {

            val uri = Uri.parse(gameUrl)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)

        }

    }

}