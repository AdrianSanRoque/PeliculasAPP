package com.example.aplicacionpeliculas

class Genre {
    var id = 0
    var name: String? = null
    override fun toString(): String {
        return name!!
    }
}