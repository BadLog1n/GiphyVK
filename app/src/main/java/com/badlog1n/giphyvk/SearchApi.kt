package com.badlog1n.giphyvk

import android.util.Log
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
                val bigGif = jsonObject.getJSONObject("images").getJSONObject("original")
                    .getString("url")
                val downSized = jsonObject.getJSONObject("images").getJSONObject("downsized")
                    .getString("url")
                val downSizedSmall = jsonObject.getJSONObject("images").getJSONObject("downsized_small")
                    .getString("mp4")
                val originalMp4 = jsonObject.getJSONObject("images").getJSONObject("original_mp4")
                    .getString("mp4")
                list.add(GifData(url, title, username, rating, time, link, bigGif, downSized, downSizedSmall, originalMp4))
            } catch (e: Exception) {
                Log.d("Error", e.toString())
                continue
            }

        }
        return list
    }


}