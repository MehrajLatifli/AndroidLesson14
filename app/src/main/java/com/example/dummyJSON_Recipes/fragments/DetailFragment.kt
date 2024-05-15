package com.example.dummyJSON_Recipes.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dummyJSON_Recipes.adapters.IngredientAdapter
import com.example.dummyJSON_Recipes.adapters.InstructionAdapter
import com.example.dummyJSON_Recipes.adapters.RecipeAdapter
import com.example.dummyJSON_Recipes.api.ApiUtils
import com.example.dummyJSON_Recipes.databinding.FragmentDetailBinding
import com.example.dummyJSON_Recipes.models.Recipe
import com.example.dummyJSON_Recipes.utils.gone
import com.example.dummyJSON_Recipes.utils.loadUrl
import com.example.dummyJSON_Recipes.utils.visible
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val api = ApiUtils.createApi()
    private val recipeAdapter = RecipeAdapter()
    private val ingredientAdapter = IngredientAdapter()
    private val instructionAdapter= InstructionAdapter()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.gone()

        val id = args.id

        val verticalLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.ingredientRecyclerView.layoutManager = verticalLayoutManager
        binding.ingredientRecyclerView.adapter = ingredientAdapter

        val horizontalLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.instructiontRecyclerView.layoutManager = horizontalLayoutManager
        binding.instructiontRecyclerView.adapter = instructionAdapter

        getRecipeById(id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getRecipeById(id: String) {

        binding.progressBar.visible()

        api.getRecipeById(id.toString()).enqueue(object : Callback<Recipe> {
            override fun onFailure(call: Call<Recipe>, t: Throwable) {


                binding.progressBar.gone()

                Log.e("API Call", "Failed to fetch recipes: ${t.message}")
            }

            override fun onResponse(call: Call<Recipe>, response: retrofit2.Response<Recipe>) {
                if (response.isSuccessful) {
                    val recipeResponse = response.body()
                    recipeResponse?.let {



                        binding.progressBar.gone()

                      /*  Picasso.get()
                            .load(it.image)
                            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                            .into(binding.imageView)

                       */

                        binding.imageView.loadUrl(it.image)


                        binding.textView.text = it.name
                        binding.textView2.text = it.rating.toString()
                        binding.textView3.text = it.cuisine.toString()

                        ingredientAdapter.updateList(it.ingredients?: emptyList())
                        instructionAdapter.updateList(it.instructions?: emptyList())
                    }
                } else {
                    Log.e("response", response.code().toString())
                }
            }
        })
    }
}
