package com.dubizzle.assignment.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dubizzle.assignment.R
import com.dubizzle.assignment.utils.auxilary.DUBIZZLE_ID
import com.dubizzle.assignment.view.fragments.DetailFragment


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        addFragment()

    }


    private fun addFragment(){
        val fragment = DetailFragment().apply {
            arguments = Bundle().apply {
                putString(
                    DUBIZZLE_ID,
                    intent.getStringExtra(DUBIZZLE_ID))
            }
        }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.detail_fragment_container, fragment)
            .commit()
    }


}