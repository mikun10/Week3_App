package com.example.WeekThree

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ListAdapter
    (private val listdata: MutableList<ListData>?) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View = layoutInflater.inflate(R.layout.activity_details, parent, false)
        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myListData = listdata!![position]
        holder.textView.text = listdata[position].description
        holder.imageView.setImageResource(listdata[position].imageId)
        holder.textView1.text = listdata[position].email
        holder.textView2.text = listdata[position].radioGroup
        holder.time.text = listdata[position].time

        holder.deleteBtn.setOnClickListener { view ->
            val builder = AlertDialog.Builder(view.context)
            builder.setMessage("Do you want to logout ?")
            builder.setCancelable(true)
            builder.setNeutralButton("Yes") { dialogInterface, which ->


                listdata.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, listdata.size)

            }
            builder.setNeutralButton("Cancel") { dialogInterface, which ->

            }
            val alert = builder.create()
            alert.show()
            true
        }
        holder.imageView.setOnClickListener { view ->

                val intent = Intent(view.context, ProfileActivity::class.java)
            intent.putExtra("name",myListData.description);
            intent.putExtra("image",myListData.imageId);
            intent.putExtra("email",myListData.email);
            intent.putExtra("gender",myListData.radioGroup);
            intent.putExtra("time",myListData.time);
            intent.putExtra("pos",position)
             view.context.startActivity(intent)

        }
    }



    override fun getItemCount(): Int {
        return listdata!!.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var deleteBtn: Button
        var textView1: TextView
        var textView2: TextView
        var textView: TextView
        var time:TextView
        var relativeLayout: RelativeLayout


        init {
            deleteBtn = itemView.findViewById<View>(R.id.deleteButton) as Button
            imageView = itemView.findViewById<View>(R.id.profileBtn) as ImageView
            textView = itemView.findViewById<View>(R.id.nameDetail) as TextView
            textView1 = itemView.findViewById<View>(R.id.emailDetail) as TextView
            textView2 = itemView.findViewById<View>(R.id.genderDetail) as TextView
            time = itemView.findViewById<View>(R.id.time) as TextView
            relativeLayout = itemView.findViewById<View>(R.id.relativeLayout) as RelativeLayout
        }
    }


}