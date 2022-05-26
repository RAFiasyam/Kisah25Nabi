package com.idn.kisah25nabi.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.idn.kisah25nabi.data.KisahResponse
import com.idn.kisah25nabi.databinding.RowItemKisahBinding
import com.idn.kisah25nabi.utils.OnItemClickCallback
import com.idn.kisah25nabi.utils.fromHttpToHttpsString


class KisahAdapter : RecyclerView.Adapter<KisahAdapter.MyViewHolder>() {

    private var listNabi = ArrayList<KisahResponse>()

    fun setData(data: List<KisahResponse>?) {
        if (data == null) return
        listNabi.clear()
        listNabi.addAll(data)
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    class MyViewHolder(val binding: RowItemKisahBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        RowItemKisahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listNabi[position]
        holder.binding.apply {
            itemName.text = data.name
            Glide.with(itemImg.context)
                .load(fromHttpToHttpsString(data.imageUrl))
                .apply(RequestOptions())
                .override(500, 500)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(itemImg)

            holder.itemView.setOnClickListener {
                onItemClickCallback?.onItemClicked(data)
            }
        }
    }

    override fun getItemCount() = listNabi.size
}