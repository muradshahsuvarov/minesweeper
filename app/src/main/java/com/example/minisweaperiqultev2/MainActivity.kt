package com.example.minisweaperiqultev2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.minisweaperiqulte.custom_adapter
import com.example.minisweaperiqulte.customlayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter = custom_adapter(this, R.layout.customlayout, data)

        gridView.adapter = adapter

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
                                item_list.add(customlayout(R.mipmap.ic_launcher,Cell(j,i,true,0,false,false)))
                                mineCount--
                            }else{
                                item_list.add(customlayout(R.mipmap.ic_launcher,Cell(j,i,false,0,false,false)))
                            }
                        }else{
                            item_list.add(customlayout(R.mipmap.ic_launcher,Cell(j,i,false,0,false,false)))
                            stopGeneratingMines = true
                        }

                    }
                }

            return item_list
        }

}