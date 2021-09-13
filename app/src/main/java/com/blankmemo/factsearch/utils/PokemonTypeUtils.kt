package com.blankmemo.factsearch.utils

import com.blankmemo.factsearch.R

object PokemonTypeUtils {

  fun getTypeColor(type: String): Int {
    return when (type) {
      "fighting" -> R.color.black
      "flying" -> R.color.black
      "poison" -> R.color.black
      "ground" -> R.color.black
      "rock" -> R.color.black
      "bug" -> R.color.black
      "ghost" -> R.color.black
      "steel" -> R.color.black
      "fire" -> R.color.black
      "water" -> R.color.black
      "grass" -> R.color.black
      "electric" -> R.color.black
      "psychic" -> R.color.black
      "ice" -> R.color.black
      "dragon" -> R.color.black
      "fairy" -> R.color.black
      "dark" -> R.color.black
      else -> R.color.black
    }
  }
}
