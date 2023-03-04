package com.badlog1n.giphyvk

import org.json.JSONArray

class SearchApi {
    fun returnJson(jsonArray: JSONArray): ArrayList<GifData> {
        val list = ArrayList<GifData>()
        for (i in 0 until jsonArray.length()) {
            try {
                val jsonObject = jsonArray.getJSONObject(i)
                val url = jsonObject.getJSONObject("images").getJSONObject("fixed_width_small")
                    .getString("url")
                val title = jsonObject.getString("title")
                val username = jsonObject.getJSONObject("user").getString("display_name")
                val time = jsonObject.getString("import_datetime")
                val rating = jsonObject.getString("rating")
                val link = jsonObject.getString("url")
                list.add(GifData(url, title, username, rating, time, link))
            } catch (e: Exception) {
                continue
            }

        }
        return list
    }


}