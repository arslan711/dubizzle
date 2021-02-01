package com.dubizzle.assignment.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dubizzle.assignment.R
import com.dubizzle.assignment.utils.auxilary.DUBIZZLE_ID
import com.dubizzle.assignment.utils.auxilary.DUBIZZLE_NAME
import com.dubizzle.assignment.view.activities.DetailActivity
import com.dubizzle.assignment.view.adapters.DubizzleRecyclerViewAdapter
import kotlinx.android.synthetic.main.list_fragment.*
import com.dubizzle.assignment.viewmodel.ListViewModel


class ListFragment : Fragment(R.layout.list_fragment) {

    private val viewModel by viewModels<ListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupRecyclerView()
        setupToolbar(view.findViewById(R.id.dubizzle_list_toolbar))
        getLiveData()


    }

    private fun getLiveData() {
        viewModel.dubizzleLiveData.observeForever{ dubizzleList ->

            dubizzle_progress_bar.visibility = when {
                dubizzleList.isEmpty() -> View.VISIBLE
                else -> View.GONE
            }



            (dubizzle_recycler_view.adapter as DubizzleRecyclerViewAdapter).submitList(dubizzleList)
        }

    }

    private fun setupRecyclerView() {
        dubizzle_recycler_view.apply {
            this.layoutManager = LinearLayoutManager(this.context)
            this.adapter = DubizzleRecyclerViewAdapter { dubizzle ->

                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DUBIZZLE_ID, dubizzle.id)
                intent.putExtra(DUBIZZLE_NAME, dubizzle.name)

                startActivity(intent)

            }
            this.addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    (this.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
    }

    private fun setupToolbar(toolbar: Toolbar) {

        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.dubizzle_detail)

    }
}
