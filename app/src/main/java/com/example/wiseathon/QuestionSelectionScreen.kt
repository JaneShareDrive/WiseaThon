package com.example.wiseathon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_question_selection_screen.*
import org.json.JSONArray
import org.json.JSONObject
import org.jetbrains.anko.toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class QuestionSelectionScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_selection_screen)
        var topicClassReturn = intent.getSerializableExtra("TopicClass") as? TopicClass  //retrieves object from previous activity
        val questReturnArray = retrieveQuestionArray()
        //button.text = topicClassReturn?.topicName.toString() + topicClassReturn?.topicID.toString()
        textTopic.text = topicClassReturn?.topicName.toString() + " Test Questions"
        questionRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@QuestionSelectionScreen)
            adapter = AnswerAdapter(questReturnArray)
        }

        buttonSubmit.setOnClickListener {
            val intent = Intent(this@QuestionSelectionScreen,AnswerActivity::class.java)
            startActivity(intent)
        }
    }

    fun sendTopicList(topicListReturned:TopicClass){
        //Sends a JSON Post that includes topic selected.  If this doesn't work, then send TopicID via URL
        val jsonObject = JSONObject()
        jsonObject.put("topicID",topicListReturned.topicID.toString())

        val queue = Volley.newRequestQueue(this)
        val url = "https://postman-echo.com/post"
        var txtResponse:String
        val request = JsonObjectRequest(Request.Method.POST,url,jsonObject,
            Response.Listener { response ->
                // Process the json
                try {
                    txtResponse = "Response: $response"
                }catch (e:Exception){
                    txtResponse = "Exception: $e"
                }

            }, Response.ErrorListener{
                // Error in request
                txtResponse = "Volley error: $it"
            })
        queue.add(request)
    }

    fun retrieveQuestionArray():List<QuestClass> {
        var strResp = """
{
"type":"QuestionClass",
"Questions":[
{
"id":1,
"Question":"Jacob lost his debit card. If Jacob reports his lost or stolen debit card within two business days, he can be held responsible for",
"Answer1":"Up to $500 unauthorized transactions.",
"Answer2":"Up to $50 unauthorized transactions.",
"Answer3":"All unauthorized transactions without a limit.",
"Answer4":"No Purchases"
},
{
"id":2,
"Question":"When a check is made payable to cash, it:",
"Answer1":"voids the check.",
"Answer2":"creates a check that anyone can cash.",
"Answer3":"does not allow the check to be deposited.",
"Answer4":"restricts the life of the check to no more than 5 days."
},
{
"id":3,
"Question":"Which of the following is a nonprofit financial institution?",
"Answer1":"Credit union.",
"Answer2":"Savings and loan bank.",
"Answer3":"Commercial bank.",
"Answer4":"Real estate agency."
},
{
"id":4,
"Question":"When would you pick invest in a Roth IRA over a Traditional IRA",
"Answer1":"When you think you will have more taxable income in the future than the present.",
"Answer2":"When you think you will have more taxable income in the present than the future.",
"Answer3":"Never",
"Answer4":"Always"
},
{
"id":5,
"Question":"When a money market fund falls below $1 per share, it is called",
"Answer1":"Breaking the bank",
"Answer2":"Breaking the buck",
"Answer3":"Inflation",
"Answer4":"Deflation"
}
]
}
"""   //replace strResponse with API Call to get actual list
        val jsonObj: JSONObject = JSONObject(strResp)
        val jsonArray: JSONArray = jsonObj.getJSONArray("Questions")
        var str_user: String = ""
        //Create Object List of topicClass
        val questList = List(jsonArray.length()){QuestClass()}
        //Fill in content from JSON Array
        for (i in 0 until jsonArray.length()) {
            var jsonInner: JSONObject = jsonArray.getJSONObject(i)
            questList[i].QuestionID = jsonInner.get("id").toString().toInt()
            questList[i].QuestionPhrase = jsonInner.get("Question").toString()
            questList[i].QuestionAns1 = jsonInner.get("Answer1").toString()
            questList[i].QuestionAns2 = jsonInner.get("Answer2").toString()
            questList[i].QuestionAns3 = jsonInner.get("Answer3").toString()
            questList[i].QuestionAns4 = jsonInner.get("Answer4").toString()
        }
        return questList
    }
}
