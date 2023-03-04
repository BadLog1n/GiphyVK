package com.badlog1n.giphyvk

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.badlog1n.giphyvk.databinding.FragmentSearchBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import org.jsoup.Connection
import org.jsoup.Jsoup


class SearchFragment : Fragment() {
    private var rcAdapter = ContentPhotoAdapter()
    private val searchApi = SearchApi()
    private var offset = 0
    private lateinit var binding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val accommRc: RecyclerView = view.findViewById(R.id.imagesRcView)
        accommRc.adapter = rcAdapter
        val linearLayoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        accommRc.layoutManager = GridLayoutManager(context, 3)

        fun loadData(search: String, offset: Int) {
            try {
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        val document: String
                        val sitePath =
                            "https://api.giphy.com/v1/gifs/search?api_key=OxaksPzPsdh9Qti793UkoLDrr3LARbpT&q=$search&limit=25&offset=$offset&rating=g&lang=en"

                        val response: Connection.Response = Jsoup.connect(sitePath)
                            .ignoreContentType(true)
                            .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
                            .timeout(10000).execute()

                        val statusCode: Int = response.statusCode()

                        if (statusCode == 200) {
                            document =
                                Jsoup.connect(sitePath).ignoreContentType(true).get().text()
                        } else throw Exception("Error")

                        //convert document to jsonObject then to jsonArray

                        val jsonObject = JSONObject(document)
                        //convert jsonObject to jsonArray
                        val jsonArray = JSONArray(jsonObject.getString("data"))

                        val result = searchApi.returnJson(jsonArray)
                        withContext(Dispatchers.Main) {
                            binding.progressBar.visibility = View.GONE
                            binding.imagesRcView.visibility = View.VISIBLE
                            for (item in result) {

                                if (binding.searchEdit.text.isNotBlank()
                                    && binding.searchEdit.text.isNotEmpty()
                                ) {
                                    rcAdapter.addPhotoRecord(item)
                                }
                            }
                            binding.foundtv.text = if (rcAdapter.itemCount != 0) "Найдено:" else "Ничего не найдено"

                        }

                    }
                }
            } catch (e: Exception) {
                Log.e("Error", e.toString())
            }
        }

        binding.searchEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.progressBar.visibility = View.VISIBLE
                rcAdapter.clearRecords()
                offset = 0
                loadData(binding.searchEdit.text.toString(), offset)
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        accommRc.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    offset += 25
                    loadData(binding.searchEdit.text.toString(), offset)
                }
            }
        })


    }


    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
/*

    private fun getAllData(inputCode: String, it: DataSnapshot) {
        val sharedPref: SharedPreferences? = activity?.getSharedPreferences(
            getString(R.string.sharedPref), Context.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor? = sharedPref?.edit()
        val map: MutableMap<String, String> = HashMap()
        map[getString(R.string.titleValue)] = it.child("title").value.toString()
        map[getString(R.string.mainTextValue)] = it.child("mainText").value.toString()
        map[getString(R.string.emailTextValue)] = it.child("email").value.toString()
        map[getString(R.string.photoPathValue)] = it.child("photo").value.toString()
        map[getString(R.string.codeValue)] = inputCode
        map[getString(R.string.imageTitleValue)] = it.child("imageTitle").value.toString()
        map[getString(R.string.contactValue)] = it.child("contact").value.toString()
        for ((key, value) in map) {
            editor?.putString(key, value)
        }

        editor?.apply()
*/


}
