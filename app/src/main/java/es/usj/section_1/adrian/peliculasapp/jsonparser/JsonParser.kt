package com.bands.hambands.jsonparser

import android.content.Context
import android.widget.Toast
import com.example.aplicacionpeliculas.Movie
import com.google.gson.Gson
import es.usj.section_1.adrian.peliculasapp.jsonparser.JsonHelper
import org.json.JSONArray
import java.io.InputStream
import java.nio.charset.Charset


class JsonParser(var context: Context){


    fun readAll(): ArrayList<Movie> {
        val json: String
        val list: ArrayList<Movie> = ArrayList();
        try {
            val inputStream: InputStream = context.assets.open("movies_data.json")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charset.forName("UTF-8"))
            val jsonArray = JSONArray(json)


            val index = jsonArray.length() -1
            for (item in 0..index) {
                var jsonObject = jsonArray.getJSONObject(item)
                val map: Map<*, *> = JsonHelper.toMap(jsonObject)
                val gson = Gson()
                val jsonElement = gson.toJsonTree(map)
                val item: Movie = gson.fromJson(jsonElement, Movie::class.java)
                list.add(item)
            }

        } catch (e: Exception) {
            Toast.makeText(context, "Json Error"+e.message, Toast.LENGTH_SHORT).show()
        }
        return list
    }

}