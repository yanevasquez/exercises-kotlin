package com.example.projetotres

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color.rgb
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var NovaCor: Button
    private lateinit var viewColor: View
    private lateinit var valueRGB: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.NovaCor = findViewById(R.id.NovaCor)
        this.viewColor = findViewById(R.id.viewColor)
        this.valueRGB = findViewById(R.id.valueRGB)

        NovaCor.setOnClickListener {
            callTelaTwo()
        }
        manterCores()
    }

    private fun callTelaTwo() {
        val telaTwo = Intent(this, CriadorTelaCor::class.java);
        startActivity(telaTwo);

    }

    private fun manterCores (){
        val listacores =  intent.extras?.getIntegerArrayList("Color")
        if (listacores != null){
            this.viewColor.setBackgroundColor(rgb(listacores[1],listacores[0],listacores[2]));
            this.valueRGB.text = "RGB(" + listacores[1].toString() + "," + listacores[0].toString() + "," + listacores[2].toString() + ")"
        }else{
            this.viewColor.setBackgroundColor(rgb(255,255,255));
            this.valueRGB.text = "#F000000";
        }
        if(this.valueRGB.text == "#000000"){
            this.valueRGB.setTextColor(rgb(255,255,255))
        }
    }
}
