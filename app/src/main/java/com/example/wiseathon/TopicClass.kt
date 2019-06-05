package com.example.wiseathon

import java.io.Serializable

//Topic Class.   Will add in Difficulty later

class TopicClass : Serializable{
    var topicID:Int = 0
    var topicName:String = ""
    var topicDifficulty: String = ""

        get() = field

        set(value) {
            field = value
        }

}