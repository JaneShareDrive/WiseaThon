package com.example.wiseathon

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import org.jetbrains.anko.toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_s_login_main.*
import org.json.JSONArray
import org.json.JSONObject
import com.example.wiseathon.QuestClass
import java.nio.file.Files.list


class SLoginMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_s_login_main)
        //Start of API Call
        getUsers()
    }

    // function for network call
    fun getUsers() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = "https://api.github.com/search/users?q=eyehunt"

        val questionList = List(2){QuestClass()}
        questionList[0].QuestionPhrase = "testphrase1"
        questionList[1].QuestionPhrase = "testphrase2"
        // Request a string response from the provided URL.
        val stringReq = StringRequest(Request.Method.GET, url,
            Listener<String> { response ->

                var strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)
                val jsonArray: JSONArray = jsonObj.getJSONArray("items")
                var str_user: String = ""
                for (i in 0 until jsonArray.length()) {
                    var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    str_user = str_user + "\n" + jsonInner.get("received_events_url") + questionList[1].QuestionPhrase

                }
                testText.text = "response : $str_user "
            },
           // Response.ErrorListener { textView!!.text = "That didn't work!" })
            Response.ErrorListener { testText.text = "That didn't work!" })
        queue.add(stringReq)

    }


}