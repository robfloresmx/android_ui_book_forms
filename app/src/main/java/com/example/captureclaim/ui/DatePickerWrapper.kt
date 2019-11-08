package com.example.captureclaim.ui

import android.app.DatePickerDialog
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import java.text.DateFormat
import java.util.*

/** A class that can turn any TextView object into a space where the user can select a date via the standard DatePickerDialog.
 * This is an ideal example of a good place to encapsulate events; you actually have three different event handlers that perform
 * a related group of actions, and maintain user interface state in a single class that can be reused throughout your application.*/

class DatePickerWrapper(private val display: TextView) : View.OnClickListener,
    View.OnFocusChangeListener,
    DatePickerDialog.OnDateSetListener {

    private val dateFormat: DateFormat = DateFormat.getDateInstance(DateFormat.LONG)

    private var dialog: DatePickerDialog

    var currentDate = Date()

    init {
        with(display) {
            isFocusable = true
            isClickable = true
            setOnClickListener(this@DatePickerWrapper)
        }
        currentDate = Date()

        val gregorianCalendar = GregorianCalendar()
        gregorianCalendar.time = currentDate

        dialog = DatePickerDialog(
            display.context, this, gregorianCalendar.get(Calendar.YEAR),
            gregorianCalendar.get(Calendar.MONTH),
            gregorianCalendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    private fun setDate(date: Date) {
        currentDate = date.clone() as Date
        display.text = dateFormat.format(currentDate)

        val gregorianCalendar = GregorianCalendar()
        gregorianCalendar.time = currentDate

        dialog.updateDate(
            gregorianCalendar.get(Calendar.YEAR),
            gregorianCalendar.get(Calendar.MONTH),
            gregorianCalendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    private fun openDatePickerDialog() {
        dialog.show()
    }

    override fun onClick(view: View) {
        openDatePickerDialog()
    }

    override fun onFocusChange(view: View, hasFocus: Boolean) {
        if (hasFocus) openDatePickerDialog()
    }

    override fun onDateSet(datePicker: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        val gregorianCalendar = GregorianCalendar(year, month, dayOfMonth)
        setDate(gregorianCalendar.time)
    }
}