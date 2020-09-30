package com.example.minisweaperiqulte

import android.app.Activity
import android.content.Context
import android.media.Image
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.example.minisweaperiqultev2.R
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.customlayout.view.*

class custom_adapter(private val getContext : Context, private val CustomLayoutId : Int,
 private val custom_item : ArrayList<customlayout>)
    : ArrayAdapter<customlayout>(getContext, CustomLayoutId, custom_item) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var row = convertView
        val Holder : ViewHolder

        if(row == null) {

            var inflater = (getContext as Activity).layoutInflater
            row = inflater!!.inflate(CustomLayoutId, parent, false)
            row.img.setOnClickListener {
                Snackbar.make(it, "Pos = " + position + " , X = " + custom_item[position].cell.x + " , Y = " + custom_item[position].cell.y
                    + ", MineAllowed = " + custom_item[position].cell.mineAllowed, Snackbar.LENGTH_LONG).show()
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