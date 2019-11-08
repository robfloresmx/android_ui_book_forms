package com.example.captureclaim.ui

import android.view.View
import android.widget.RadioGroup
import android.widget.TextView

class IconPickerWrapper(private val label: TextView) : RadioGroup.OnCheckedChangeListener {

    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
        val radioButton = group.findViewById<View>(checkedId)
        label.text = radioButton.contentDescription
    }
}