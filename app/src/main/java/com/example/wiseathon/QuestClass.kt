package com.example.wiseathon

import java.io.Serializable

//Class for Questions
class QuestClass : Serializable {
     var QuestionID: Int = 0
     var QuestionPhrase: String = ""

    //On next refactor, turn possible answers into an array to make it dynamic
     var QuestionAns1: String  = ""
     var QuestionAns2: String  = ""
     var QuestionAns3: String  = ""
     var QuestionAns4: String  = ""

         get() = field

         set(value) {
             field = value
         }

}