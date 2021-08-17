package com.example.WeekThree


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    var list: List<ListData>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list = ArrayList<ListData>()


        val fab: View = findViewById(R.id.fab)
        var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

  
            if (result.resultCode == Activity.RESULT_OK) {

                var data = result.data

                val name = data?.getStringExtra("name").toString()
                val email = data?.getStringExtra("email").toString()
                val gender = data?.getStringExtra("gender").toString()
                val time = data?.getStringExtra("time").toString()


                (list as ArrayList<ListData>).add(ListData(R.drawable.pictureOne,email,name,gender,time))

                val recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
                val adapter = ListAdapter(list as ArrayList<ListData>)
                recyclerView.setHasFixedSize(true)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = adapter

                val insertIndex = 2
        adapter.notifyItemInserted(insertIndex);

            }
        }
        fab.setOnClickListener { view ->
            val intent= Intent(this,RegistrationPage::class.java)

            resultLauncher.launch(intent)

       }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return when (id) {
            R.id.item1 -> {

                val builder = AlertDialog.Builder(this)
                builder.setMessage("Are You sure to logout?")
                builder.setCancelable(true)
                builder.setPositiveButton(
                    "Yes"
                ) { dialog, id -> dialog.cancel() }
                builder.setNegativeButton(
                    "No"
                ) { dialog, id -> dialog.cancel() }
                val alert = builder.create()
                alert.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

