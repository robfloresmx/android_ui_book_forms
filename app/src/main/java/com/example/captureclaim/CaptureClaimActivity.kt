package com.example.captureclaim

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.captureclaim.ui.DatePickerWrapper
import com.example.captureclaim.ui.IconPickerWrapper

import kotlinx.android.synthetic.main.activity_capture_claim.*
import kotlinx.android.synthetic.main.content_main.*

class CaptureClaimActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var selectedDate: DatePickerWrapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capture_claim)
        setSupportActionBar(toolbar)
        //Initialize Views
        /**You don't need to hold any other references to the dateTextView,
         * since you'll only ever need to access it through the DatePickerWrapper class.*/
        attach.setOnClickListener(this)
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_ATTACH_PERMISSION -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    onAttachClick()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_ATTACH_FILE -> onAttachFileResult(resultCode, data)
        }
    }

    private fun onAttachFileResult(resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK || data == null || data.data == null)
            return

        Toast.makeText(this, data.dataString, Toast.LENGTH_SHORT).show()
    }

    private fun onAttachClick() {
        val permissionStatus =
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_ATTACH_PERMISSION
            )
            return
        }
        val attachIntent = Intent(Intent.ACTION_GET_CONTENT)
            .addCategory(Intent.CATEGORY_OPENABLE)
            .setType("*/*")
        startActivityForResult(attachIntent, REQUEST_ATTACH_FILE)
    }

    override fun onClick(view: View) {
        when (view.id) {
            attach.id -> onAttachClick()
        }
    }

    companion object {
        const val REQUEST_ATTACH_FILE = 1
        const val REQUEST_ATTACH_PERMISSION = 1001
    }
}
