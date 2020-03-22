package com.shantanu.example.modelbasic.ui.customview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.shantanu.example.modelbasic.R

@SuppressLint("AppCompatCustomView")
class ATextView(context: Context, attrs: AttributeSet) : TextView(context, attrs) {

    private var typefaceType: Int = 0
    private var themeColoredText: Boolean = false

    /* init {
         val array = context.theme.obtainStyledAttributes(
             attrs,
             //R.styleable.ATextView,
             0, 0
         )

             try {
                 typefaceType = array.getInteger(R.styleable.ATextView_font_name, 0)
             } finally {
                 array.recycle()
             }

         }*/

}