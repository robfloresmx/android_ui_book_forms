package com.example.captureclaim

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import com.example.captureclaim.ui.DatePickerWrapper
import com.example.captureclaim.ui.IconPickerWrapper

import kotlinx.android.synthetic.main.activity_capture_claim.*
import kotlinx.android.synthetic.main.content_main.*

class CaptureClaimActivity : AppCompatActivity() {

    lateinit var selectedDate: DatePickerWrapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capture_claim)
        setSupportActionBar(toolbar)
        //Initialize Views
        /**You don't need to hold any other references to the dateTextView,
         * since you'll only ever need to access it through the DatePickerWrapper class.*/
        selectedDate = DatePickerWrapper(input_date)
        categories.setOnCheckedChangeListener(IconPickerWrapper(selected_category))
        categories.check(R.id.other)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
