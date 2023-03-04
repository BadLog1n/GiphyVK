package com.badlog1n.giphyvk

import android.content.Context
import org.json.JSONArray

class SearchApi {
    fun returnJson(jsonArray: JSONArray): ArrayList<GifData> {
        val list = ArrayList<GifData>()
        for (i in 0 until jsonArray.length()) {
            try {
                val jsonObject = jsonArray.getJSONObject(i)
                val url = jsonObject.getJSONObject("images").getJSONObject("fixed_width_small").getString("url")
                list.add(GifData(url))
            }
            catch (e: Exception) {
                continue
            }

        }
        return list
    }


}