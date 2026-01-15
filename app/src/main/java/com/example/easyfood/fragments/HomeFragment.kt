package com.example.easyfood.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.easyfood.activities.MainActivity
import com.example.easyfood.activities.MealActivity
import com.example.easyfood.adapters.MostPopularAdapter
import com.example.easyfood.databinding.FragmentHomeBinding
import com.example.easyfood.pojo.Meal
import com.example.easyfood.viewModel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var randomMeal: Meal
    private lateinit var binding: FragmentHomeBinding

    private lateinit var popularitemsAdapter: MostPopularAdapter
    companion object{
      const val  MEAL_ID=" com.example.easyfood.fragment.idMeal"
        const val  MEAL_NAME=" com.example.easyfood.fragment.nameMeal"
        const val  MEAL_THUMB=" com.example.easyfood.fragment.thumbMeal"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // homeMvvm= ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel = (activity as MainActivity).viewModel

        popularitemsAdapter = MostPopularAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preparePopularItemsRecyclerView()

        viewModel.getRandomMeal()
        observeRandomMeal()
        onRandomMealClick()
        viewModel.getPopularItems()
        observePopularItemsLiveData()
        onPopularItemClick()



    }

    private fun onPopularItemClick() {
       popularitemsAdapter.onItemClick = {
           meal->
           val intent = Intent(activity, MealActivity:: class.java)
           intent.putExtra(MEAL_ID,meal.idMeal)
           intent.putExtra(MEAL_NAME,meal.strMeal)
           intent.putExtra(MEAL_THUMB,meal.strMealThumb)
           startActivity(intent)
       }
    }

    private fun preparePopularItemsRecyclerView() {
        binding.recViewMealsPopular.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularitemsAdapter
        }
    }

    private fun observePopularItemsLiveData() {
        viewModel.observePopularItemsLiveData().observe(viewLifecycleOwner,
            { mealList->
                popularitemsAdapter.setMeals(ArrayList(mealList))

            })

    }

    private fun onRandomMealClick() {
        binding.randomMealCard.setOnClickListener {
            val intent= Intent(activity, MealActivity:: class.java)
            intent.putExtra(MEAL_ID ,randomMeal.idMeal)
            intent.putExtra(MEAL_NAME ,randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB , randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observeRandomMeal(){
        viewModel.observeRandomMealLivedata().observe(viewLifecycleOwner,
            //object : Observer<Meal?> {

                //override fun onChanged(t: Meal?) {
                    // if (t==null)
                    //Log.d("HomeFragment", "API response: ")
                    { meal ->
                        if( meal== null)
                            return@observe
                        Log.d("HomeFragment", "Meal recieved: ${meal.strMeal}")
                    Glide.with(this@HomeFragment)
                        .load(meal!!.strMealThumb)
                        .into(binding.imgRandomMeal)

                        this.randomMeal=meal

            })
    }

}


