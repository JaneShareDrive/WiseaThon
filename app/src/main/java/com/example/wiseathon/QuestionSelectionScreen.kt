package com.example.wiseathon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_question_selection_screen.*
import org.json.JSONArray
import org.json.JSONObject


class QuestionSelectionScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_selection_screen)
        var strTopic:String = intent.getStringExtra("Topic")
        val questReturnArray = retrieveQuestionArray()

        questionRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@QuestionSelectionScreen)
            adapter = QuestionAdapter(questReturnArray)
        }
    }


    fun retrieveQuestionArray():List<QuestClass> {
        var strResp = """
{
"type":"QuestionClass",
"Questions":[
{
"id":1,
"Question":"When would you pick invest in a Roth IRA over a Traditional IRA",
"Answer1":"When you think you will have more taxable income in the future than the present.",
"Answer2":"When you think you will have more taxable income in the present than the future.",
"Answer3":"Never",
"Answer4":"Always"
},
{
"id":1,
"Question":"When would you pick invest in a Roth IRA over a Traditional IRA",
"Answer1":"When you think you will have more taxable income in the future than the present.",
"Answer2":"When you think you will have more taxable income in the present than the future.",
"Answer3":"Never",
"Answer4":"Always"
},
{
"id":1,
"Question":"When would you pick invest in a Roth IRA over a Traditional IRA",
"Answer1":"When you think you will have more taxable income in the future than the present.",
"Answer2":"When you think you will have more taxable income in the present than the future.",
"Answer3":"Never",
"Answer4":"Always"
},
{
"id":1,
"Question":"When would you pick invest in a Roth IRA over a Traditional IRA",
"Answer1":"When you think you will have more taxable income in the future than the present.",
"Answer2":"When you think you will have more taxable income in the present than the future.",
"Answer3":"Never",
"Answer4":"Always"
},
{
"id":2,
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
