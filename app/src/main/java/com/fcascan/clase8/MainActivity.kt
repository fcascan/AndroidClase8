package com.fcascan.clase8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.fcascan.clase8.model.Pokemon

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    lateinit var pokeList: MutableList<Pokemon>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        viewModel.getDataFromFirebase()

        viewModel.pokemonList.observe(this) {
            pokeList = it
            Log.d("MainActivity", "Lista: ${pokeList.toString()}")
        }
        viewModel.screenState.observe(this) { state ->
            when(state) {
                LoadingState.LOADING -> {
                    //Show loading
                }
                LoadingState.SUCCESS -> {
                    //Hide loading
                }
                LoadingState.FAILURE -> {
                    //Show error, hide loading
                }
                else -> {
                    //Show error, hide loading
                }
            }
        }
    }
}