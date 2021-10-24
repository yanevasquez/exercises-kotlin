package com.example.projetoum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.projetoum.R.id.text_view
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var numbersField: TextView
    private lateinit var array: MutableList<Int>
    private lateinit var background: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.numbersField = findViewById(text_view)
        this.background = findViewById(R.id.new_view);
        this.background.setOnClickListener(ClickListener())
    }

    fun createColors(): MutableList<Int>{
        var lista:MutableList<Int> = mutableListOf();
        while(lista.size < 3){
            lista.add(Random.nextInt(0,255))
        }
        return lista
    }

    fun showView(){
        this.array = createColors();
        this.numbersField.text = array.toString();
        this.background.setBackgroundColor(android.graphics.Color.rgb(array[0],array[1],array[2]));
    }

    inner class ClickListener: View.OnClickListener{
        override fun onClick(v: View?) {
            this@MainActivity.showView()
        }
    }


}