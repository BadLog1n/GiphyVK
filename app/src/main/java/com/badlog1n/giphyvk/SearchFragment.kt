package com.badlog1n.giphyvk

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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
        accommRc.layoutManager = GridLayoutManager(context, 2)



        /* database = FirebaseDatabase.getInstance().getReference("key")
         binding.submit.setOnClickListener {
             val inputCode = binding.codeInputText.text
             if (inputCode.toString().isNotEmpty()) {
                 database.child(inputCode.toString()).get().addOnSuccessListener {
                     if (it.exists()) {
                         getAllData(inputCode.toString(), it)
                         inputCode.clear()
                         view.hideKeyboard()
                         view.findNavController().navigate(R.id.contentFragment)
                     } else Toast.makeText(requireContext(), "Код не найден", Toast.LENGTH_SHORT)
                         .show()
                 }
             } else Toast.makeText(requireContext(), "Код не может быть пустым", Toast.LENGTH_SHORT)
                 .show()*/


/*        binding.question.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("О приложении")
            builder.setMessage("Чтобы перейти к описанию объекта, введите код в поле ниже и нажмите кнопку \"далее\"")
            builder.setPositiveButton("OK") { _, _ -> }
            builder.setNeutralButton("GitHub") { _, _ ->
                val openLink = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/badlog1n"))
                startActivity(openLink)
            }
            builder.setNegativeButton("GitHub проекта") { _, _ ->
                val openLink =
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/badlog1n/Pictale"))
                startActivity(openLink)
            }
            builder.show()

        }*/

        fun loadData() {
            try {
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        val document: String
                        val sitePath =
                            "https://api.giphy.com/v1/gifs/search?api_key=OxaksPzPsdh9Qti793UkoLDrr3LARbpT&q=search&limit=25&rating=g&lang=ru"

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
                            rcAdapter.clearRecords()
                            for (item in result) {
                                rcAdapter.addPhotoRecord(item)

                                if (binding.searchEdit.text.isNotBlank()
                                    && binding.searchEdit.text.isNotEmpty()
                                ) {
                                    rcAdapter.addPhotoRecord(item)
                                }
                            }
                            if (rcAdapter.itemCount == 0) {
                                binding.foundtv.text = "Ничего не найдено"
                            }
                        }

                    }
                }
            } catch (e: Exception) {
                Log.e("Error", e.toString())
            }
        }

        loadData()


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
