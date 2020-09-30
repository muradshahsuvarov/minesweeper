package com.example.minisweaperiqultev2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.minisweaperiqulte.custom_adapter
import com.example.minisweaperiqulte.customlayout
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    companion object{
        var flagIsAllowed = false
        var gameDone : Boolean = false
        var restartButton : Button? = null
        var choiceButton : Button? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter = custom_adapter(this, R.layout.customlayout, data)

        gridView.adapter = adapter

        restartButton = findViewById(R.id.restartBtn)
        restartBtn.setBackgroundColor(Color.GREEN)

        choiceButton = findViewById(R.id.choiceBtn)
        choiceBtn.setBackgroundColor(Color.YELLOW)

        restartBtn.setOnClickListener {
            adapter = custom_adapter(this, R.layout.customlayout, data)
            gridView.adapter = adapter
            restartBtn.setBackgroundColor(Color.GREEN)
            choiceBtn.setBackgroundColor(Color.YELLOW)
            gameDone = false
        }

        choiceBtn.setOnClickListener {
            if(flagIsAllowed == false){
                choiceBtn.text = "Put a flag"
                flagIsAllowed = true
            }else{
                choiceBtn.text = "Try a field"
                flagIsAllowed = false
            }
        }

    }


    val data : ArrayList<customlayout>
        get()
        {
            var item_list : ArrayList<customlayout> = ArrayList<customlayout>()

                var cell_list = arrayOf<Array<Cell>>() // 2D Array representing matrix
                var mineCount : Int = 3 // total # of mines in the matrix
                var stopGeneratingMines : Boolean = false // If 3 mines are generated in the matrix, then stop generating them, otherwise start again


                for(i in 0..4){
                    for(j in 0..4){
                        if(mineCount != 0){
                            var randNumber : Int = Random.nextInt(0,5)
                            if(randNumber == 3){
                                item_list.add(customlayout(R.drawable.box,Cell(j,i,true,0,false,false)))
                                mineCount--
                            }else{
                                item_list.add(customlayout(R.drawable.box,Cell(j,i,false,0,false,false)))
                            }
                        }else{
                            item_list.add(customlayout(R.drawable.box,Cell(j,i,false,0,false,false)))
                            stopGeneratingMines = true
                        }

                    }
                }



            return item_list
        }

}