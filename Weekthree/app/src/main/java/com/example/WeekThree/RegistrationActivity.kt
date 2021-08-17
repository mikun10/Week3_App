package com.example.WeekThree

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.*
import android.widget.Toast
import java.io.Serializable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registration.*
import java.text.SimpleDateFormat
import java.util.*

class RegistrationPage : AppCompatActivity() {


    private lateinit var PersonName :EditText
    private lateinit var email:EditText
    private lateinit var button2:Button
    private lateinit var button_date_1:Button
    private lateinit var date:TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var mPickTimeBtn :Button
    private lateinit var textView:TextView
    var cal = Calendar.getInstance()


    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)


        PersonName = findViewById(R.id.PersonName)
        email = findViewById(R.id.email)
        button2 = findViewById(R.id.button2)
        radioGroup = findViewById(R.id.radioGrp)
        button_date_1 = findViewById(R.id.button_date_1)
        date = findViewById(R.id.date)
        mPickTimeBtn = findViewById(R.id.pickTimeBtn)
        textView     = findViewById(R.id.timeTv)


        mPickTimeBtn.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                textView.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }



        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        button_date_1!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@RegistrationPage,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })


        button2.setOnClickListener{
            val Name =  PersonName.text.toString().trim()
            val Email =  email.text.toString().trim()
            if (Name.isEmpty()){
                PersonName.error="User name required"

            } else if(Name.length< 5){
                PersonName.error="Name is too short"
            }
            else if (Email.isEmpty()){
                email.error="Email required"

            }
            else if (!email.text.toString().contains("@gmail.com")){
                email.setError("Enter valid Email id !")
            }
           else if (!maleBtn.isChecked() and  !femaleBtn.isChecked()){
                Toast.makeText(getApplicationContext(), "Please select a gender !",Toast.LENGTH_LONG).show();
                return@setOnClickListener
            }
            else {

                if(maleBtn.isChecked){
                    sendUserData(Name,Email,"Male",date.text.toString(),textView.text.toString())
                }else{
                    sendUserData(Name,Email,"Female",date.text.toString(),textView.text.toString())
                }

            }


        }
    }

    private fun updateDateInView() {
        val myFormat = "DD/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        date!!.text = sdf.format(cal.getTime())
    }

    private fun sendUserData(username: String, useremail: String,gender: String,date:String,time:String) {

        val send = Intent(this,MainActivity::class.java)

        send.putExtra("name",username+"("+date+")")
        send.putExtra("email",useremail)
        send.putExtra("gender",gender)
        send.putExtra("time",time)
        setResult(RESULT_OK, send);
        finish();

    }

}


class UserInfo : Serializable {
    private lateinit var name: String
    private lateinit var email: String
    fun getName(): String? {
        return name
    }
    @JvmName("setName1")
    fun setName(name: String?) {
        this.name = name!!
    }
    fun getEmail(): String? {
        return email
    }
    @JvmName("setEmail1")
    fun setEmail(email: String?) {
        this.email= email!!
    }
}