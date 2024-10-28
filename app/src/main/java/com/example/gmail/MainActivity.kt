package com.example.gmail

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gmail.Adapters.ItemAdapter
import com.example.gmail.Models.GmailModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity(), ItemClickListener {
    lateinit var gmails: MutableList<GmailModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun getTime(before: Int): String {
            val calendar = Calendar.getInstance()
            // Trừ đi before phút
            calendar.add(Calendar.MINUTE, -before)
            // Định dạng thời gian theo kiểu "hh:mm a"
            val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
            return formatter.format(calendar.time)
        }

        gmails = mutableListOf <GmailModel> ()
        repeat(12) {
            gmails.add(
                GmailModel("Store ${it+1}", "Subject ${it+1}: Thank you for your invitation! Let's try ... ",
                    getTime(it*32),
                    resources.getIdentifier("avatar${it + 1}", "drawable", packageName))
            )
        }

        val adapter =ItemAdapter(gmails, this)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
//        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider, null)) // Sử dụng drawable tùy chỉnh
        recyclerView.addItemDecoration(dividerItemDecoration)

    }

    override fun OnItemClicked(position: Int) {
        Log.v("TAG", "${gmails[position]}")
    }
}