package com.example.minisweaperiqulte

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.media.Image
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import com.example.minisweaperiqultev2.MainActivity
import com.example.minisweaperiqultev2.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.customlayout.view.*

class custom_adapter(private val getContext : Context, private val CustomLayoutId : Int,
 private val custom_item : ArrayList<customlayout>)
    : ArrayAdapter<customlayout>(getContext, CustomLayoutId, custom_item) {

    var nonMineTiles : Int = 0
    var stopCountingTiles : Boolean = false

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var row = convertView
        val Holder : ViewHolder
        var mineCounter : Int = 0


        if(row == null) {

            if(stopCountingTiles == false){
                for(i in 0..24){
                    if(custom_item[i].cell.mineAllowed == false){
                        nonMineTiles++
                    }
                }
                stopCountingTiles = true
            }


            var inflater = (getContext as Activity).layoutInflater
            row = inflater!!.inflate(CustomLayoutId, parent, false)

                row.img.setOnClickListener {



                    if(MainActivity.flagIsAllowed == false && MainActivity.gameDone == false){

                        if(custom_item[position].cell.mineAllowed == true){ // If mine is clicked
                            Snackbar.make(it, "BOOM!!!!", Snackbar.LENGTH_LONG).show()
                            row.img.setImageResource(R.drawable.mine)
                            MainActivity.gameDone = true // Stop the game
                            mineCounter = 0
                            MainActivity.restartButton!!.setBackgroundColor(Color.RED)
                        }else if(custom_item[position].cell.mineAllowed == false && custom_item[position].cell.opened == false){ // If mine is not clicked
                            custom_item[position].cell.opened = true
                            nonMineTiles--

                            var x_left : Int = custom_item[position].cell.x - 1
                            var x_right : Int = custom_item[position].cell.x + 1
                            var y_down : Int = custom_item[position].cell.y - 1
                            var y_up : Int = custom_item[position].cell.y + 1

                            // LEFT: - (1,0)
                            if(x_left > 0){
                                var x_cord = x_left
                                var y_cord = custom_item[position].cell.y
                                for(i in 0..24){
                                    if(custom_item[i].cell.x == x_cord && custom_item[i].cell.y == y_cord && custom_item[i].cell.opened == false){
                                        if(custom_item[i].cell.mineAllowed == true){
                                            mineCounter++
                                            break
                                        }
                                    }
                                }
                            }
                            // RIGHT: + (1,0)
                            if(x_right <= 4){
                                var x_cord = x_right
                                var y_cord = custom_item[position].cell.y
                                for(i in 0..24){
                                    if(custom_item[i].cell.x == x_cord && custom_item[i].cell.y == y_cord && custom_item[i].cell.opened == false){
                                        if(custom_item[i].cell.mineAllowed == true){
                                            mineCounter++
                                            break
                                        }
                                    }
                                }
                            }
                            // DOWN: - (0,1)
                            if(y_down > 0){
                                var x_cord = custom_item[position].cell.x
                                var y_cord = y_down
                                for(i in 0..24){
                                    if(custom_item[i].cell.x == x_cord && custom_item[i].cell.y == y_cord && custom_item[i].cell.opened == false){
                                        if(custom_item[i].cell.mineAllowed == true){
                                            mineCounter++
                                            break
                                        }
                                    }
                                }
                            }
                            // UP: + (0,1)
                            if(y_up <= 4){
                                var x_cord = custom_item[position].cell.x
                                var y_cord = y_up
                                for(i in 0..24){
                                    if(custom_item[i].cell.x == x_cord && custom_item[i].cell.y == y_cord && custom_item[i].cell.opened == false){
                                        if(custom_item[i].cell.mineAllowed == true){
                                            mineCounter++
                                            break
                                        }
                                    }
                                }
                            }


                            // Show how many mines are nearby
                            if(mineCounter == 0){
                                row.img.setImageResource(R.drawable.pizza)
                            }else if(mineCounter == 1){
                                row.img.setImageResource(R.drawable.one)
                            }else if(mineCounter == 2){
                                row.img.setImageResource(R.drawable.two)
                            }else if(mineCounter == 3){
                                row.img.setImageResource(R.drawable.three)
                            }

                            mineCounter = 0
                        }
                    }else if(MainActivity.flagIsAllowed == true && MainActivity.gameDone == false){
                        if(custom_item[position].cell.mineAllowed == false){ // If mine is clicked
                            MainActivity.restartButton!!.setBackgroundColor(Color.RED)
                            Snackbar.make(it, "BOOM!!!!", Snackbar.LENGTH_LONG).show()
                            MainActivity.gameDone = true // Stop the game
                        }else{
                            row.img.setImageResource(R.drawable.flag)
                        }
                    }

                    // If total number of non mine tiles are opened , then player wins
                    if(nonMineTiles == 0){
                        MainActivity.gameDone = true
                        Snackbar.make(it, "Murad Shahsuvarov IQULTE", Snackbar.LENGTH_LONG).show()
                    }

                }

            Holder = ViewHolder()
            Holder.img = row!!.findViewById(R.id.img) as ImageView
            row.setTag(Holder)
        }else{
            Holder = row.getTag() as ViewHolder
        }

        val item = custom_item[position]

        Holder.img!!.setImageResource(item.image)

        return row
    }

    class ViewHolder{
        internal var img: ImageView? = null
    }

}