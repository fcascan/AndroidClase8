package com.fcascan.clase8.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fcascan.clase8.model.Pokemon
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FragmentOneViewModel : ViewModel() {
    private val db = Firebase.firestore
    private val pokemonList = MutableLiveData<MutableList<Pokemon>>()
    var pokemon = MutableLiveData<Pokemon>()
    val screenState = MutableLiveData<LoadingState>()

    fun getAllFromFirebase() {
        screenState.value = LoadingState.LOADING
        db.collection("pokemons")
            .get()
            .addOnSuccessListener { snapshot ->
                val list = snapshot.toObjects(Pokemon::class.java)
                pokemonList.postValue(list)
                screenState.postValue(LoadingState.SUCCESS)
            }
            .addOnFailureListener { exception ->
                screenState.postValue(LoadingState.FAILURE)
            }
    }

    fun getIdFromFirebase(idNumber: String) {
        screenState.value = LoadingState.LOADING
        db.collection("pokemons")
            .whereEqualTo("id", idNumber.toInt())
            .get()
            .addOnSuccessListener { snapshot ->
                val list = snapshot.toObjects(Pokemon::class.java)
                Log.d("FragmentOne", "Pokemon: " + list[0].toString())
                pokemon.postValue(list[0])
                screenState.postValue(LoadingState.SUCCESS)
            }
            .addOnFailureListener { exception ->
                Log.d("FragmentOne", "ERROR: $exception.toString()")
                screenState.postValue(LoadingState.FAILURE)
            }
    }
}