package com.dubizzle.assignment.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dubizzle.assignment.R
import com.dubizzle.assignment.repository.local.database.pojo.Dubizzle
import com.dubizzle.assignment.utils.auxilary.DateUtil
import com.dubizzle.customimagecache.PicassoCache


class DubizzleRecyclerViewAdapter(private val onClickListener: (Dubizzle) -> Unit) :
    ListAdapter<Dubizzle, DubizzleViewHolder>(DubizzleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DubizzleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_dubizzle, parent, false)
        return DubizzleViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: DubizzleViewHolder, position: Int) {
        holder.item = getItem(position)
    }
}

class DubizzleViewHolder(private val view: View, private val onClickListener: (Dubizzle) -> Unit) :
    RecyclerView.ViewHolder(view) {

    var item: Dubizzle? = null
        set(value) {
            value?.let { newValue ->
                field = newValue
                view.setOnClickListener { onClickListener(newValue) }
                view.findViewById<TextView>(R.id.dubizzle_name_text_view).text = newValue.name
                view.findViewById<TextView>(R.id.dubizzle_price_text_view).text =newValue.price
                view.findViewById<TextView>(R.id.dubizzle_time_since_text_view).text =DateUtil.timeSince(newValue.createdAtTime)
                PicassoCache.load(
                    newValue.images?.get(0),view.findViewById(R.id.dubizzle_image_view),
                270,
                270)
            }
        }
}

class DubizzleDiffCallback : DiffUtil.ItemCallback<Dubizzle>() {

    override fun areItemsTheSame(oldItem: Dubizzle, newItem: Dubizzle): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Dubizzle, newItem: Dubizzle): Boolean {
        return oldItem == newItem
    }
}
