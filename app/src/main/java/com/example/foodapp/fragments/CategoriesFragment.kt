package com.example.foodapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodapp.activities.CategoryMealsActivity
import com.example.foodapp.activities.MainActivity
import com.example.foodapp.adapters.AllCategoiesAdapater
import com.example.foodapp.databinding.FragmentCategoriesBinding
import com.example.foodapp.pojo.CategoriesMealsList
import com.example.foodapp.pojo.CategoryList

class CategoriesFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var allCategoriesAdapter: AllCategoiesAdapater
    private val homeViewModel by lazy { (activity as MainActivity).homeViewModel }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoriesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        allCategoriesAdapter = AllCategoiesAdapater()
//        homeViewModel.getAllCategories()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAllCategoriesList()
        prepareRv()
        categoriesItemClicked()
    }

    private fun categoriesItemClicked() {
        allCategoriesAdapter.itemClick = { category ->
            val intent = Intent(activity, CategoryMealsActivity::class.java)
            intent.putExtra(HomeFragment.CATEGORY_NAME, category.strCategory)
            startActivity(intent)

        }
    }

    private fun prepareRv() {
        binding.RvCategory.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.RvCategory.adapter = allCategoriesAdapter
    }

    private fun observeAllCategoriesList() {
        homeViewModel.observerAllCategoriesList()
            .observe(viewLifecycleOwner, object : Observer<List<CategoriesMealsList.Category>> {
                override fun onChanged(value: List<CategoriesMealsList.Category>) {
                    allCategoriesAdapter.setAllCategories(value as ArrayList<CategoriesMealsList.Category>)
                }

            })
    }
}
