package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity : Activity(), View.OnClickListener, VolumnView.ValueInterface {
    var buttonVolumeLines: Button? = null
    var buttonVolumeValue: Button? = null
    var editTextVolumeValue: EditText? = null
    var editTextVolumeLines: EditText? = null
    var textViewValue: TextView? = null
    var vv: VolumnView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonVolumeLines = findViewById<View>(R.id.buttonVolumeLines) as Button
        buttonVolumeValue = findViewById<View>(R.id.buttonVolumeValue) as Button
        editTextVolumeValue = findViewById<View>(R.id.editTextVolumeValue) as EditText
        editTextVolumeLines = findViewById<View>(R.id.editTextVolumeLines) as EditText
        textViewValue = findViewById<View>(R.id.textViewValue) as TextView
        vv = findViewById<View>(R.id.volumnView1) as VolumnView
        vv!!.setInterfaceClick(this)
        buttonVolumeLines!!.setOnClickListener(this)
        buttonVolumeValue!!.setOnClickListener(this)
    }

    var progress = 10f
    override fun onClick(v: View) {
        vv!!.setProgress(editTextVolumeValue!!.text!!.toString().toFloat())
        vv!!.setLinesS(editTextVolumeValue!!.text!!.toString().toInt())
    }

    override fun ValueChange(x: Float) {
        textViewValue!!.text = "Volume set at: " + x.toInt() + "%"
    }
}
