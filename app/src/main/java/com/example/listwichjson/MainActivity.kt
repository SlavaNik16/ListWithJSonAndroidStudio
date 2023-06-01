package com.example.listwichjson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.listwichjson.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipeList = Recipe.getRecipesFromFile("recipes.json",this)

        val adapter = RecipeAdapter(this,recipeList)
        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { _, _, position, _ ->
            val selectedRecipe = recipeList[position]
            val detailIntent = RecipeDetailActivity.newIntent(this,selectedRecipe)
            startActivity(detailIntent)
        }
    }
}