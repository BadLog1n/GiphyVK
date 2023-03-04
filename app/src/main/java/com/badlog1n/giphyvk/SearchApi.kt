package com.badlog1n.giphyvk

import android.content.Context
import org.json.JSONArray

class SearchApi {
    fun returnJson(jsonArray: JSONArray): ArrayList<GifData> {
        val list = ArrayList<GifData>()
        for (i in 1 until jsonArray.length()) {
            try {
                val jsonObject = "none"
                val name = "none"
                val fedDistrict = "none"
                val region = "none"
                val city = "none"
                val photo = "none"
                val cost = "none"
                val organization = "none"
                val type = "none"
                val food = "none"
                val sort = "none"
                list.add(GifData(name, fedDistrict, region, city, photo, cost, organization, type, food, sort))
            }
            catch (e: Exception) {
                continue
            }

        }
        return list
    }


}