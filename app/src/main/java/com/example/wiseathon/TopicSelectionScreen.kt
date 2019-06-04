package com.example.wiseathon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_topic_selection_screen.*

class TopicSelectionScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_selection_screen)
        var strTopic:String = intent.getStringExtra("Topic")
        textView2.text = strTopic

    }
}
