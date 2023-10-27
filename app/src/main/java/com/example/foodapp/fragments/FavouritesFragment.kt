package com.example.foodapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.activities.MainActivity
import com.example.foodapp.adapters.FavouritesListAdapter
import com.example.foodapp.databinding.FragmentFavouritesBinding
import com.example.foodapp.pojo.MealsList
import com.example.foodapp.viewmodels.HomeViewModel
import com.google.android.material.snackbar.Snackbar


class FavouritesFragment() : Fragment() {

    lateinit var homeViewModel: HomeViewModel
    lateinit var favouritesListAdapter: FavouritesListAdapter
    private lateinit var binding: FragmentFavouritesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentFavouritesBinding.inflate(layoutInflater)
        homeViewModel = (activity as MainActivity).homeViewModel
        favouritesListAdapter = FavouritesListAdapter()


    }

    private fun prepareFavouritesListRV() {
        binding.apply {
            favRV.layoutManager =
                GridLayoutManager(requireContext(), 2)
            favRV.adapter = favouritesListAdapter

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeFavs()
        prepareFavouritesListRV()

        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
//
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val meal=favouritesListAdapter.differ.currentList[position]
                homeViewModel.deleteMeal(favouritesListAdapter.differ.currentList[position])
                Snackbar.make(requireView(), "meal Deleted", Snackbar.LENGTH_SHORT)
                    .setAction("Undo"){
                        homeViewModel.insertMeal(meal)

                    }.show()
            }

        }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.favRV)
    }

    private fun observeFavs() {
        homeViewModel.observeFavouriteMeals()
            .observe(viewLifecycleOwner, object : Observer<List<MealsList.Meal>> {
                override fun onChanged(value: List<MealsList.Meal>) {
                    favouritesListAdapter.differ.submitList(value)
                }

            })
    }


}
