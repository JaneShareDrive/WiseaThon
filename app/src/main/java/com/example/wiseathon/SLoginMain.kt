package com.example.wiseathon

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_s_login_main.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject



class SLoginMain : AppCompatActivity() {
    internal lateinit var sp:Spinner
    lateinit var topicListsObject: List<TopicClass>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_s_login_main)
        textName.text = intent.getStringExtra("User")
      textRole.text = intent.getStringExtra("Role")
        // create spinner and populate with information from API
        sp = findViewById(R.id.dropDrownTopics) as Spinner

        getUsers()

        submitTextButton.setOnClickListener {
            val intent = Intent(this@SLoginMain,QuestionSelectionScreen::class.java)
            val selectedTopicClass = topicListsObject[sp.selectedItemPosition]
            intent.putExtra("TopicClass",selectedTopicClass)
            startActivity(intent)
        }

    }

fun setTopicListObject(topicListTemp:List<TopicClass>){
    //Sets TopicList array for carry over to next activity due.  Called by asynchronous volley
    topicListsObject = topicListTemp
}


    //Example functions for use
    fun getUsers(){//:String {
        // Instantiate the RequestQueue.  Is Asynchronous.  Retrieves list of topics from api
        val queue = Volley.newRequestQueue(this)
        val url: String = "https://gettopics.azurewebsites.net/api/Function1"
        //var strResp = """[{"topicID":"I","topicName":"Personal Finance"},{"topicID":"II","topicName":"Banking"},{"topicID":"III","topicName":"Credit"},{"topicID":"IV","topicName":"Insurance"},{"topicID":"V","topicName":"Investments"}]"""
        val stringReq = StringRequest(Request.Method.GET, url,
            Listener<String> { response ->

                var strResp = response.toString()
                var strConst = """ {"type":"Stuff","Tarit":"""
                var strConstEnd = """}"""
                strResp = strConst + strResp + strConstEnd

                val jsonObj: JSONObject = JSONObject(strResp)
                val jsonArray: JSONArray = jsonObj.getJSONArray("Tarit")
                //Create Object List of topicClass
                var topicList = List(jsonArray.length()){TopicClass()}
                //Fill in content from JSON Array
                for (i in 0 until jsonArray.length()) {
                    var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    topicList[i].topicID = i//jsonInner.get("topicID").toString().toInt()
                    topicList[i].topicName = jsonInner.get("topicName").toString()
                }
                val strtopicListArray: ArrayList<String> = ArrayList()
                for (i in 0 until topicList.count())  //Converts topic object to string list for spinner selection
                {
                    strtopicListArray.add(topicList[i].topicName)
                }

                sp = findViewById(R.id.dropDrownTopics) as Spinner
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,strtopicListArray)
                sp.adapter = adapter
                setTopicListObject(topicList)
            },

            Response.ErrorListener {var txtResponse = "That didn't work!" })
        queue.add(stringReq)
    }
    fun sampleJson() {
        // SampleJsonCode
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
               // textView5.text = "response : $str_user "
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
    fun jsonParsetoObjectSample() {
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