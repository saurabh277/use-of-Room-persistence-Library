package com.example.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomDatabase.Builder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    val db by lazy {
        Room.databaseBuilder(this,
            AppDatabase::class.java,
            "User.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                db.userDao().insert(User("Saurabh Bisht", "7895328007", "dehradun", 22))
            }
        }
       /* button2.setOnClickListener {

            runBlocking {
                val list = GlobalScope.async(Dispatchers.IO) { db.userDao().getAllUser() }
                if (list.await().isNotEmpty()) {
                    with(list.await()[0]) {
                        textView1.text = name
                        textView2.text = number
                        textView3.text = address
                        textView4.text = age.toString()
                    }
                }
            }
        }*/
        db.userDao().getAllUser().observe(this, Observer {list->
            if (list.isNotEmpty()) {
                with(list[0]) {
                    textView1.text = name
                    textView2.text = number
                    textView3.text = address
                    textView4.text = age.toString()
                }
            }
        }

        )
    }
}
