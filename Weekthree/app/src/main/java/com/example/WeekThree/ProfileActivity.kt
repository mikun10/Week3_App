package com.example.WeekThree

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {

    lateinit var homebtn: Button
    lateinit var del: Button
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val bundle: Bundle? = intent.extras
        val name = bundle!!.getString("name")
        val img = bundle!!.getInt("image")
        val email = bundle!!.getString("email")
        val gender = bundle!!.getString("gender")
        val time = bundle!!.getString("time")
        NAME1.text = "Name: " + name
        EMAIL.text = "Email: " + email
        timeTV.text= "Time: " + time
        imageView.setImageResource(img)
        textView6.text = "Gender: " +gender


        homebtn = findViewById(R.id.homeBtn)
        del = findViewById(R.id.deleteButton)
        homebtn.setOnClickListener {

            val backIntent = Intent(this, MainActivity::class.java)
            startActivity(backIntent)
        }
        del.setOnClickListener {
            val builder1 = AlertDialog.Builder(this)
            builder1.setMessage("Are You sure to delete?")
            builder1.setCancelable(true)
            builder1.setPositiveButton(

                "Yes",


            ) { dialog, id -> dialog.cancel() }
            builder1.setNegativeButton(
                "No"
            ) { dialog, id -> dialog.cancel() }
            val alert11 = builder1.create()
            alert11.show()
            true
        }

    }
}
