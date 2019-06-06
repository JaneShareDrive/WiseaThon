package com.example.wiseathon

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_s_login_main.*
import org.jetbrains.anko.toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import com.example.wiseathon.QuestClass
import com.example.wiseathon.TopicClass
import java.nio.file.Files.list


class SLoginMain : AppCompatActivity() {
    internal lateinit var sp:Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_s_login_main)
        textName.text = intent.getStringExtra("User")
      textRole.text = intent.getStringExtra("Role")
        // create spinner and populate with information from API
        sp = findViewById(R.id.dropDrownTopics) as Spinner
        val topicListsObject = retrieveTopicList2()  //Call API and retrieve topic List and return list of topic Object
        val strtopicListArray: ArrayList<String> = ArrayList()
        for (i in 0 until topicListsObject.count())  //Converts topic object to string list for spinner selection
        {
            strtopicListArray.add(topicListsObject[i].topicName)
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,strtopicListArray)

        sp.adapter = adapter
        submitTextButton.setOnClickListener {
            //startActivity(Intent(this, TopicSelectionScreen::class.java))
           // val intent = Intent(this@SLoginMain,TopicSelectionScreen::class.java)
            val intent = Intent(this@SLoginMain,QuestionSelectionScreen::class.java)
            val selectedTopicClass = topicListsObject[sp.selectedItemPosition]
            intent.putExtra("TopicClass",selectedTopicClass)
            startActivity(intent)
        }
        button3.setOnClickListener {
            getUsers()
        }
    }


    // function for network call
    fun retrieveTopicList(): List<TopicClass> {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = "https://gettopics.azurewebsites.net/api/Function1"
        var strResp = """
{
"type":"Stuff",
"Topics":[
{
"id":1,
"title":"Banking"
},
{
"id":2,
"title":"Credit"
},
{
"id":3,
"title":"Personal Finance"
},
{
"id":4,
"title":"Money/Financial Planning"
},
{
"id":5,
"title":"Investments"
}
]
}
"""   //replace strResponse with API Call to get actual list
        val jsonObj: JSONObject = JSONObject(strResp)
        val jsonArray: JSONArray = jsonObj.getJSONArray("Topics")
        var str_user: String = ""
        //Create Object List of topicClass
        val topicList = List(jsonArray.length()){TopicClass()}
        //Fill in content from JSON Array
        for (i in 0 until jsonArray.length()) {
            var jsonInner: JSONObject = jsonArray.getJSONObject(i)
            topicList[i].topicID = jsonInner.get("id").toString().toInt()
            topicList[i].topicName = jsonInner.get("title").toString()
        }
        return topicList

    }



    fun retrieveTopicList2(): List<TopicClass> {
        //replace
        var strResp = """
{
"type":"Stuff",
"Topics":[
{
"id":1,
"title":"Banking"
},
{
"id":2,
"title":"Credit"
},
{
"id":3,
"title":"Personal Finance"
},
{
"id":4,
"title":"Money/Financial Planning"
},
{
"id":5,
"title":"Investments"
}
]
}
"""   //replace strResponse with API Call to get actual list
        val jsonObj: JSONObject = JSONObject(strResp)
        val jsonArray: JSONArray = jsonObj.getJSONArray("Topics")
        var str_user: String = ""
        //Create Object List of topicClass
        val topicList = List(jsonArray.length()){TopicClass()}
        //Fill in content from JSON Array
        for (i in 0 until jsonArray.length()) {
            var jsonInner: JSONObject = jsonArray.getJSONObject(i)
            topicList[i].topicID = jsonInner.get("id").toString().toInt()
            topicList[i].topicName = jsonInner.get("title").toString()
        }
        return topicList

    }

    //Example functions for use
    fun getUsers() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = "https://gettopics.azurewebsites.net/api/Function1"

        /*//Testing array list of objects
        val questionList = List(2){QuestClass()}
        questionList[0].QuestionPhrase = "testphrase1"
        questionList[1].QuestionPhrase = "testphrase2"
        */

        // Request a string response from the provided URL.
        val stringReq = StringRequest(Request.Method.GET, url,
            Listener<String> { response ->

                var strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)
                val jsonArray: JSONArray = jsonObj.getJSONArray("topicID")
                var str_user: String = ""
                for (i in 0 until jsonArray.length()) {
                    var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    str_user = str_user + "\n" + jsonInner.get("topicName") /*+ questionList[1].QuestionPhrase*/

                }
                textView5.text = "response : $str_user "
            },
            // Response.ErrorListener { textView!!.text = "That didn't work!" })
            Response.ErrorListener {var txtResponse = "That didn't work!" })
        queue.add(stringReq)

    }
    fun getUsers2() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = "https://api.github.com/search/users?q=eyehunt"

        /*//Testing array list of objects
        val questionList = List(2){QuestClass()}
        questionList[0].QuestionPhrase = "testphrase1"
        questionList[1].QuestionPhrase = "testphrase2"
        */

        // Request a string response from the provided URL.
        val stringReq = StringRequest(Request.Method.GET, url,
            Listener<String> { response ->

                var strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)
                val jsonArray: JSONArray = jsonObj.getJSONArray("items")
                var str_user: String = ""
                for (i in 0 until jsonArray.length()) {
                    var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    str_user = str_user + "\n" + jsonInner.get("received_events_url") /*+ questionList[1].QuestionPhrase*/

                }
                textView5.text = "response : $str_user "
            },
            // Response.ErrorListener { textView!!.text = "That didn't work!" })
            Response.ErrorListener {var txtResponse = "That didn't work!" })
        queue.add(stringReq)

    }
    fun jsonParseExample() {
        // Example for JSON Parsing
        var strResp = """
    {
       "type":"Stuff",
       "Topics":[
          {
             "id":1,
             "title":"Banking"
          },
          {
             "id":2,
             "title":"Credit"
          },
          {
             "id":3,
             "title":"Personal Finance"
          },
          {
             "id":4,
             "title":"Money/Financial Planning"
          },
          {
             "id":5,
             "title":"Investments"
          }
       ]
    }
"""
        val jsonObj: JSONObject = JSONObject(strResp)
        val jsonArray: JSONArray = jsonObj.getJSONArray("Topics")
        var str_user: String = ""
        for (i in 0 until jsonArray.length()) {
            var jsonInner: JSONObject = jsonArray.getJSONObject(i)
            str_user = str_user + "\n" + jsonInner.get("title") /*+ questionList[1].QuestionPhrase*/

        }
        var txtResponse2 = "response : $str_user "
    }
    fun jsonParsetoObject() {
        // Example for JSON Parsing to Object
//Testing array list of objects


        var strResp = """
{
"type":"Stuff",
"Topics":[
{
"id":1,
"title":"Banking"
},
{
"id":2,
"title":"Credit"
},
{
"id":3,
"title":"Personal Finance"
},
{
"id":4,
"title":"Money/Financial Planning"
},
{
"id":5,
"title":"Investments"
}
]
}
"""
        val jsonObj: JSONObject = JSONObject(strResp)
        val jsonArray: JSONArray = jsonObj.getJSONArray("Topics")
        var str_user: String = ""
        //Create Object List of topicClass
        val topicList = List(jsonArray.length()){TopicClass()}
        //Fill in content from JSON Array
        for (i in 0 until jsonArray.length()) {
            var jsonInner: JSONObject = jsonArray.getJSONObject(i)
            topicList[i].topicID = jsonInner.get("id").toString().toInt()
            topicList[i].topicName = jsonInner.get("title").toString()
            str_user = str_user + "\n" + topicList[i].topicID + "  " + topicList[i].topicName  /*+ questionList[1].QuestionPhrase*/

        }
        var txtResponse3 = "response : $str_user "
    }



}