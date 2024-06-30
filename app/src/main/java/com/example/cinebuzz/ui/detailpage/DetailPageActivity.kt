package com.example.cinebuzz.ui.detailpage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DetailPageActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, DetailPageFragment.newInstance())
            .commit()
    }
}