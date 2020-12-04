package com.hemanth.idrive.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hemanth.idrive.data.model.QuestionResponse
import com.hemanth.idrive.databinding.QuestionItemBinding
import com.hemanth.idrive.utilities.AnswerType

/**
 *<h1>QuestionAdapter</h1>
 * @author : Hemanth     hemanthappu006@gmail.com
 * @version : 1.0
 */
class QuestionAdapter(private val list: ArrayList<QuestionResponse.Tree.Item>) :
    RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    var onItemSelectedListener: ((answerLink: String, type: AnswerType) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder =
        QuestionViewHolder(
            QuestionItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.binding.data = list[position]
        holder.binding.grpAnswer.visibility =
            if (list[position].isSelected) View.VISIBLE else View.GONE

        holder.binding.root.setOnClickListener {
            list[position].isSelected = !list[position].isSelected
            holder.binding.grpAnswer.visibility =
                if (list[position].isSelected) View.VISIBLE else View.GONE
        }

        holder.binding.tvQuestionChromeView.setOnClickListener {
            onItemSelectedListener?.invoke(list[position].answer, AnswerType.CHROME)
        }

        holder.binding.tvQuestionWebView.setOnClickListener {
            onItemSelectedListener?.invoke(list[position].answer, AnswerType.WEB)
        }
    }

    class QuestionViewHolder(val binding: QuestionItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}