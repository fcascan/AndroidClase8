package com.fcascan.clase8

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fcascan.clase8.model.Pokemon
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

enum class LoadingState {
    LOADING,
    SUCCESS,
    FAILURE
}

class MainActivityViewModel : ViewModel() {
    private val db = Firebase.firestore
    val pokemonList = MutableLiveData<MutableList<Pokemon>>()
    val screenState = MutableLiveData<LoadingState>()

    fun getDataFromFirebase() {
        screenState.value = LoadingState.LOADING
        db.collection("pokemons")
            .get()
            .addOnSuccessListener { snapshot ->
                val list = snapshot.toObjects(Pokemon::class.java)
                pokemonList.postValue(list)
                screenState.postValue(LoadingState.SUCCESS)
//                val list = mutableListOf<Pokemon>()
//                for (document in snapshot) {
//                    val pokemon = Pokemon(
//                        document.data["id"] as Int,
//                        document.data["name"] as Map<String, String>,
//                        document.data["type"] as List<String>,
//                        document.data["base"] as Map<String, Int>,
//                        document.data["imageURL"] as String
//                    )
//                    list.add(pokemon)
//                }
//                pokemonList.value = list
//                screenState.value = LoadingState.SUCCESS
            }
            .addOnFailureListener { exception ->
                screenState.postValue(LoadingState.FAILURE)
            }
    }

}