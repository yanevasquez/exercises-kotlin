package com.example.projetodois

import android.graphics.Color.rgb
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.SeekBar
import java.lang.Integer.*

class MainActivity : AppCompatActivity() {
    private lateinit var progressColors: View
    private lateinit var textColor: TextView
    private lateinit var background: View
    private lateinit var seek1: SeekBar
    private lateinit var seek2: SeekBar
    private lateinit var seek3: SeekBar
    private lateinit var hexColors: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.seek1 = findViewById(R.id.seekBar1)
        this.seek1.setOnSeekBarChangeListener(OnChange())

        this.seek2= findViewById(R.id.seekBar2)
        this.seek2.setOnSeekBarChangeListener(OnChange())

        this.seek3 = findViewById(R.id.seekBar3)
        this.seek3.setOnSeekBarChangeListener(OnChange())

        this.progressColors = findViewById(R.id.seekColors)
        this.background = findViewById(R.id.showViewColor)
        this.textColor = findViewById(R.id.colorsHex)

    }

    fun createProgress(): MutableList<Int>{
        var list:MutableList<Int> = mutableListOf()

        list.add(this.seek1.progress)
        list.add(this.seek2.progress)
        list.add(this.seek3.progress)

        return list
    }

    fun changeColors(array: MutableList<Int>){
        this.background.setBackgroundColor(
            rgb(
                array[0],
                array[1],
                array[2],
            ),
        )

    }

    fun setRbgToHex(array: MutableList<Int>){
        this.hexColors = toHexString(rgb(array[0],array[1],array[2])).substring(2,8).uppercase()
        this.textColor.text =hexColors

    }

    inner class OnChange : SeekBar.OnSeekBarChangeListener {
        private lateinit var listColors: MutableList<Int>

        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            this.listColors= createProgress()
            this@MainActivity.changeColors(listColors)
            this@MainActivity.setRbgToHex(listColors)
        }
        override fun onStartTrackingTouch(seekBar: SeekBar?) {}
        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    }
}