package com.example.easyfood.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyfood.db.MealDatabase
import com.example.easyfood.pojo.CategoryList
import com.example.easyfood.pojo.CategoryMeals

import com.example.easyfood.pojo.Meal
import com.example.easyfood.pojo.MealList
import com.example.easyfood.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(
    private val mealDatabase: MealDatabase
) : ViewModel() {
    private var randomMealLiveData = MutableLiveData<Meal>()
    private var popularItemsLiveData = MutableLiveData<List<CategoryMeals>>()

    private var favoriteMealsLiveData = mealDatabase.mealDao().getAllMeal()
    fun getRandomMeal() {
        RetrofitInstance.foodApi.getRandomMeal().enqueue(object : retrofit2.Callback<MealList> {
            override fun onFailure(
                call: Call<MealList?>,
                t: Throwable
            ) {
                Log.d("Homefragment", t.message.toString())

            }

            override fun onResponse(call: Call<MealList?>, response: Response<MealList?>) {
                if (response.body() != null) {
                    val randomMeal: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value = randomMeal

                } else {
                    return
                }
            }

        })
    }

    fun getPopularItems(){
        RetrofitInstance.foodApi.getPopularItems("Seafood").enqueue(object : Callback<CategoryList>{
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                if (response.body() != null) {
                    popularItemsLiveData.value= response.body()!!.meals
                }

            }
            override fun onFailure(
                call: Call<CategoryList>,
                t: Throwable){
                Log.d("Homefragment", t.message.toString())

                }
        })

    }

        fun observeRandomMealLivedata(): LiveData<Meal> {
        return randomMealLiveData
    }
        fun observePopularItemsLiveData(): LiveData<List<CategoryMeals>>{
            return popularItemsLiveData
        }
    fun observeFavoritesMealsLiveData(): LiveData<List<Meal>>{
        return favoriteMealsLiveData
    }

    }
