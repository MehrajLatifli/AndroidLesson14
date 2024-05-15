package com.example.dummyJSON_Recipes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyJSON_Recipes.R
import com.example.dummyJSON_Recipes.databinding.ItemRecipeBinding
import com.example.dummyJSON_Recipes.models.Recipe
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso


class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.UserViewHolder>() {

    private val List = arrayListOf<Recipe>()

    lateinit var onClickItem: (String) -> Unit

    inner class UserViewHolder(val itemRecipeBinding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(itemRecipeBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return List.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val item = List[position]

        holder.itemRecipeBinding.recipe =item

        Picasso.get()
            .load(item.image)
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .into(holder.itemRecipeBinding.imageView)


       /* val slideAnimation = AnimationUtils.loadAnimation(holder.itemRecipeBinding.root.context, com.google.android.material.R.anim.m3_side_sheet_enter_from_left)
        holder.itemRecipeBinding.textView.startAnimation(slideAnimation)


        */

        holder.itemRecipeBinding.recipeMaterialCardView.setOnClickListener {

            onClickItem.invoke(item.id.toString())
        }



    }

    fun updateList(newList: List<Recipe>) {
        List.clear()
        List.addAll(newList)
        notifyDataSetChanged()
    }
}