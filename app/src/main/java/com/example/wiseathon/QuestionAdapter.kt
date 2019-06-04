package com.example.wiseathon

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row_question.view.*

class QuestionAdapter(private val questReturnArray: List<QuestClass>) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {


    override fun getItemCount(): Int {
        return questReturnArray.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_question,parent,false)
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textQuestPhrase.text = questReturnArray[position].QuestionPhrase
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textQuestPhrase = view.textQuestPhrase!!

    }

}
