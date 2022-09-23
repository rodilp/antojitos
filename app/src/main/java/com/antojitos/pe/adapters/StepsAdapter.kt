package com.antojitos.pe.adapters

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antojitos.pe.R
import com.antojitos.pe.model.Steps
import kotlinx.android.synthetic.main.instructions_items.view.*


class StepsAdapter(private val context: Context) : RecyclerView.Adapter<StepsAdapter.StepsViewHolder>() {


    private var dataList = mutableListOf<Steps>()

    fun setDataList(data: MutableList<Steps>) {
        dataList = data
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsViewHolder {

        val view: View = LayoutInflater.from(context).inflate(R.layout.instructions_items, parent, false)
        return StepsViewHolder(view)
    }

    override fun onBindViewHolder(holder: StepsViewHolder, position: Int) {

        val steps: Steps = dataList[position]
        holder.bindView(steps)

    }

    override fun getItemCount(): Int {
        return if (dataList.size > 0) {
            dataList.size
        } else {
            0
        }
    }


    inner class StepsViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun bindView(steps: Steps) {
            itemView.tvi_title.text = steps.title
            itemView.tvi_description.text = steps.description
        }

    }

}