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

                val username = jsonObject.getString("username")
                val time = jsonObject.getString("import_datetime")
                val rating = jsonObject.getString("rating")
                val link = jsonObject.getString("url")
                val bigGif =
                    jsonObject.getJSONObject("images").getJSONObject("original").getString("url")
                val downSized =
                    jsonObject.getJSONObject("images").getJSONObject("downsized").getString("url")
                val downSizedSmall =
                    jsonObject.getJSONObject("images").getJSONObject("fixed_height")
                        .getString("url")
                val originalMp4 =
                    jsonObject.getJSONObject("images").getJSONObject("preview_gif").getString("url")
                list.add(
                    GifData(
                        url,
                        title,
                        username,
                        rating,
                        time,
                        link,
                        bigGif,
                        downSized,
                        downSizedSmall,
                        originalMp4
                    )
                )
            } catch (e: Exception) {
                Log.d("Error in API", e.toString())
                continue
            }

        }
        return list
    }


}