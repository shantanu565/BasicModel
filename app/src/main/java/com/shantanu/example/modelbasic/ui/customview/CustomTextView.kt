package com.shantanu.example.modelbasic.ui.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import com.shantanu.example.modelbasic.R

class CustomTextView(context: Context, attrs: AttributeSet) : androidx.appcompat.widget.AppCompatTextView(context, attrs) {

    private var typefaceType: Int = 0
    private var themeColoredText: Int = resources.getColor(R.color.colorPrimaryDark)

    init {
        this.setTextColor(themeColoredText)
    }

    fun CustomTextView(context: Context) {
        val face = Typeface.createFromAsset(context.assets, "Helvetica_Neue.ttf")
        this.setTypeface(face)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}