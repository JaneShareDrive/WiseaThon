package com.example.wiseathon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_answer.*
import org.json.JSONArray
import org.json.JSONObject

class AnswerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)


        val questReturnArray = retrieveQuestionArray()
        recyclerAnswer.apply {
            layoutManager = LinearLayoutManager(this@AnswerActivity)
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

