package com.example.wiseathon

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
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
        holder.radQuestAns1.text = questReturnArray[position].QuestionAns1
        holder.radQuestAns2.text = questReturnArray[position].QuestionAns2
        holder.radQuestAns3.text = questReturnArray[position].QuestionAns3
        holder.radQuestAns4.text = questReturnArray[position].QuestionAns4
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textQuestPhrase = view.textQuestPhrase!!
        val radQuestAns1 = view.radioButAns1!!
        val radQuestAns2 = view.radioButAns2!!
        val radQuestAns3 = view.radioButAns3!!
        val radQuestAns4 = view.radioButAns4!!

    }

}
