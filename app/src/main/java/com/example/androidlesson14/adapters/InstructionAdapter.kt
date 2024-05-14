package com.example.androidlesson14.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlesson14.databinding.ItemInstructionBinding

class InstructionAdapter : RecyclerView.Adapter<InstructionAdapter.InstructionViewHolder>() {

    private val List = mutableListOf<String>()

    inner class InstructionViewHolder(val itemInstructionBinding: ItemInstructionBinding) :
        RecyclerView.ViewHolder(itemInstructionBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionViewHolder {
        val binding = ItemInstructionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InstructionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return List.size
    }

    override fun onBindViewHolder(holder: InstructionViewHolder, position: Int) {
        val instruction = List[position]
           holder.itemInstructionBinding.textView.text = instruction
    }

    fun updateList(newList: List<String>) {
        List.clear()
        List.addAll(newList)
        notifyDataSetChanged()
    }
}