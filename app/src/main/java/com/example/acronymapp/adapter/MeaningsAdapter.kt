package com.example.acronymapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.acronymapp.databinding.ItemAcronymBinding
import com.example.acronymapp.model.Lfs

class MeaningsAdapter : RecyclerView.Adapter<MeaningsAdapter.MeaningsViewHolder>() {

    private val items = mutableListOf<Lfs>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningsViewHolder =
        ItemAcronymBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).let { MeaningsViewHolder(it) }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: MeaningsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun loadMeanings(items: List<Lfs>) {
        this.items.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    class MeaningsViewHolder(private val binding: ItemAcronymBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(lfs: Lfs) {
            binding.lf = lfs.lf
        }
    }
}