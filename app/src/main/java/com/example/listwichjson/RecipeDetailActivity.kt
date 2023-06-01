package com.example.listwichjson

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listwichjson.databinding.ActivityRecipeDetailBinding

class RecipeDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecipeDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.extras?.getString(EXTRA_TITLE)
        val url = intent.extras?.getString(EXTRA_URL)

        setTitle(title)
        binding.webView.loadUrl(url.toString())
    }

    companion object{
        const val EXTRA_TITLE = "title"
        const val EXTRA_URL = "url"

        fun newIntent(context:Context,recipe: Recipe): Intent {
            val detailIntent = Intent(context,RecipeDetailActivity::class.java)

            detailIntent.putExtra(EXTRA_TITLE,recipe.title)
            detailIntent.putExtra(EXTRA_URL,recipe.instructionUrl)
            return detailIntent
        }
    }
}