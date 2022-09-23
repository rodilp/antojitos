package com.antojitos.pe.adapters

import android.R
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.antojitos.pe.model.SliderItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.smarteist.autoimageslider.SliderViewAdapter

/*
class SliderAdapterExample(context: Context) :
    SliderViewAdapter<SliderAdapterExample.SliderAdapterVH>() {
    private val context: Context
    private var mSliderItems: MutableList<SliderItem> = ArrayList()
    fun renewItems(sliderItems: MutableList<SliderItem>) {
        mSliderItems = sliderItems
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        mSliderItems.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItem(sliderItem: SliderItem) {
        mSliderItems.add(sliderItem)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.image, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        val sliderItem: SliderItem = mSliderItems[position]
        viewHolder.textViewDescription.setText(sliderItem.getDescription())
        viewHolder.textViewDescription.textSize = 16f
        viewHolder.textViewDescription.setTextColor(Color.WHITE)
        Glide.with(viewHolder.itemView)
            .load(sliderItem.getImageUrl())
            .fitCenter()
            .into<Target<Drawable>>(viewHolder.imageViewBackground)
        viewHolder.itemView.setOnClickListener(object : View.OnClickListener() {
            fun onClick(v: View?) {
                Toast.makeText(context, "This is item in position $position", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    override fun getCount(): Int {
        return mSliderItems.size
    }

    internal inner class SliderAdapterVH(itemView: View) : ViewHolder(itemView) {
        var itemView: View
        var imageViewBackground: ImageView
        var imageGifContainer: ImageView
        var textViewDescription: TextView

        init {
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider)
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container)
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider)
            this.itemView = itemView
        }
    }

    init {
        this.context = context
    }
}*/