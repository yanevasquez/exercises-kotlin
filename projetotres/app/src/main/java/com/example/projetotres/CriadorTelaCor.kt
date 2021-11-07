package com.example.projetotres

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.rgb
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import java.lang.Integer.*

class CriadorTelaCor : AppCompatActivity() {
    private lateinit var progressColors: View
    private lateinit var textColor: TextView
    private lateinit var background: View
    private lateinit var seek1: SeekBar
    private lateinit var seek2: SeekBar
    private lateinit var seek3: SeekBar
    private lateinit var hexColors: String
    private lateinit var redirectTela: Button
    private lateinit var btnsave: Button

       override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_criador_tela_cor)

            this.seek1 = findViewById(R.id.seekBar1)
            this.seek1.setOnSeekBarChangeListener(OnChange())
            this.seek2= findViewById(R.id.seekBar2)
            this.seek2.setOnSeekBarChangeListener(OnChange())
            this.seek3 = findViewById(R.id.seekBar3)
            this.seek3.setOnSeekBarChangeListener(OnChange())

            this.progressColors = findViewById(R.id.seekColors)
            this.background = findViewById(R.id.viewColor)
            this.textColor = findViewById(R.id.colorsHex)
            this.redirectTela=findViewById(R.id.bntcancel)
            this.btnsave=findViewById(R.id.btnsave)


           redirectTela.setOnClickListener{
               callTelaOne()
           }

           btnsave.setOnClickListener{
               redirectToMain(createProgress())
           }
    }

    private fun redirectToMain(values: MutableList<Int>){
        val params = Bundle()
        val listColors:ArrayList<Int> = arrayListOf()
        val telaOne = Intent(this,MainActivity::class.java)
        listColors.add(values[1])
        listColors.add(values[0])
        listColors.add(values[2])
        params.putIntegerArrayList("Color", listColors)
        telaOne.putExtras(params)
        startActivity(telaOne)
    }
    private fun callTelaOne(){
        val secondScreen = Intent(this,MainActivity::class.java);
        startActivity(secondScreen)
    }

    fun createProgress(): MutableList<Int>{
            var list:MutableList<Int> = mutableListOf()

            list.add(this.seek1.progress)
            list.add(this.seek2.progress)
            list.add(this.seek3.progress)

            return list
        }

    inner class OnChange : SeekBar.OnSeekBarChangeListener {
        private lateinit var listColors: MutableList<Int>
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            this.listColors= createProgress()
            this@CriadorTelaCor.changeColors(listColors)
            this@CriadorTelaCor.setRbgToHex(listColors)
        }
        override fun onStartTrackingTouch(seekBar: SeekBar?) {}
        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
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

    @SuppressLint("SetTextI18n")
    fun setRbgToHex(array: MutableList<Int>){
        this.hexColors = toHexString(rgb(array[0],array[1],array[2])).substring(2,8).uppercase()
        this.textColor.text = "#$hexColors";
        if (hexColors == "#000000"){
            this.textColor
                .setTextColor(android.graphics.Color.rgb(255,255,255))
        }else{
            this.textColor.setTextColor(android.graphics.Color.rgb(0,0,0))
        }
    }

}


