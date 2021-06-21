package com.example.aplicacionpeliculas

 class Movie {
    var id = 0
    var title: String? = null
    var description: String? = null
    var director: String? = null
    var year = 0
    var runtime = 0
    var rating = 0f
    var votes = 0
    var revenue = 0f
    lateinit var genres: IntArray
    lateinit var actors: IntArray


    override fun toString(): String {
        return "Movie ID:$id, title: $title, year: $year"
    }
}