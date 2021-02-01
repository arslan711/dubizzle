package com.dubizzle.assignment.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dubizzle.assignment.R
import com.dubizzle.assignment.view.fragments.ListFragment

class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        addFragment()
    }


    private fun addFragment(){

        val fragment = ListFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.home_fragment_container, fragment)
            .commit()

    }

}
