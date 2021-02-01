package com.dubizzle.assignment.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.dubizzle.assignment.R
import com.dubizzle.assignment.utils.auxilary.DUBIZZLE_ID
import com.dubizzle.assignment.utils.auxilary.DateUtil
import com.dubizzle.assignment.viewmodel.DetailViewModel
import com.dubizzle.customimagecache.PicassoCache
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.appbar.CollapsingToolbarLayout


class DetailFragment : Fragment(R.layout.detail_fragment) {

    private val viewModel by viewModels<DetailViewModel>()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



        arguments?.let { bundle ->

            viewModel.dubizzleLiveData(bundle.getString(DUBIZZLE_ID)!!).observe(viewLifecycleOwner) { dubizzleOrNull ->

                dubizzleOrNull?.let { dubizzle ->

                    setupImageOffsetListener(
                        view.findViewById(R.id.dubizzle_app_bar_layout),
                        view.findViewById(R.id.dubizzle_image_view),
                        view.findViewById(R.id.dubizzle_collapsing_toolbar_layout))

                    setupToolbar(
                        view.findViewById(R.id.dubizzle_detail_toolbar))

                    val displayMetrics = view.context!!.resources.displayMetrics
                    val dpWidth = displayMetrics.widthPixels / displayMetrics.density
                    val dpHeight = displayMetrics.widthPixels / displayMetrics.density

                    PicassoCache.load(
                        dubizzle.images?.get(0),
                        view.findViewById(R.id.dubizzle_image_view),
                        (dpHeight*.95f).toInt(),
                        dpWidth.toInt()
                    )
                    view.findViewById<TextView>(R.id.dubizzle_name_text_view).text = dubizzle.name
                    view.findViewById<TextView>(R.id.dubizzle_price_text_view).text =dubizzle.price
                    view.findViewById<TextView>(R.id.dubizzle_time_since_text_view).text = DateUtil.timeSince(dubizzle.createdAtTime)

                }
            }
        }
    }

    private fun setupToolbar(toolbar: Toolbar){

        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }

    }

    private fun setupImageOffsetListener(appBarLayout: AppBarLayout, imageView: ImageView,collapsingToolbarLayout: CollapsingToolbarLayout){

        appBarLayout.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val range = (-appBarLayout.totalScrollRange).toFloat()
            imageView.imageAlpha = (255 * (1.0f - verticalOffset.toFloat() / range)).toInt()
            if (imageView.imageAlpha==0){
                collapsingToolbarLayout.title = getString(R.string.dubizzle_detail)
            } else {
                collapsingToolbarLayout.title = " "
            }
        })
    }

}