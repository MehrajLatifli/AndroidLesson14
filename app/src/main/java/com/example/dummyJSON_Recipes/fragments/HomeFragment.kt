package com.example.dummyJSON_Recipes.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dummyJSON_Recipes.R
import com.example.dummyJSON_Recipes.adapters.RecipeAdapter
import com.example.dummyJSON_Recipes.api.ApiUtils
import com.example.dummyJSON_Recipes.databinding.FragmentHomeBinding
import com.example.dummyJSON_Recipes.models.Recipe
import com.example.dummyJSON_Recipes.models.RecipeResponse
import com.google.android.gms.common.api.Response
import retrofit2.Call
import retrofit2.Callback


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    private val recipeAdapter =   RecipeAdapter()
    private val api = ApiUtils.createApi()





    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.visibility=View.GONE



        val horizontalLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recycleViewHome.layoutManager = horizontalLayoutManager
        binding.recycleViewHome.adapter = recipeAdapter







      /*  val gridLayoutManager = GridLayoutManager(context, 4)
        binding.recycleViewHome2.layoutManager = gridLayoutManager
        binding.recycleViewHome2.adapter = nameAdapter

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)
        binding.recycleViewHome3.layoutManager = staggeredGridLayoutManager
        binding.recycleViewHome3.adapter = nameAdapter

       */


        getRecipe()

        recipeAdapter.onClickItem={item ->

            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(item))
        }
    }


    private fun getRecipe() {

        binding.progressBar.visibility=View.VISIBLE

        api.getRecipe().enqueue(object : Callback<RecipeResponse> {

            override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {

                binding.progressBar.visibility=View.GONE
                Log.e("API Call", "Failed to fetch recipes: ${t.message}")

            }

            override fun onResponse(
                call: Call<RecipeResponse>,
                response: retrofit2.Response<RecipeResponse>
            ) {
                if (response.isSuccessful) {
                    val recipeResponse = response.body()
                    recipeResponse?.let {
                        val recipes = recipeResponse.recipes ?: emptyList()
                        for (item in recipes) {
                            Log.e("RecipeItem", item.toString())
                        }

                        binding.progressBar.visibility=View.GONE

                        recipeAdapter.updateList(recipes)
                    }
                } else {
                    Log.e("response", response.code().toString())
                }
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}