package com.example.foodapp.fragments.bottomSheet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodapp.R
private const val MEAL_ID = "param1"
class BottomFragmentSheet : Fragment() {
    // TODO: Rename and change types of parameters
    private var mealID: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mealID = it.getString(MEAL_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
    }

    companion object {

        @JvmStatic fun newInstance(param1: String, ) =
                BottomFragmentSheet().apply {
                    arguments = Bundle().apply {
                        putString(MEAL_ID, param1)
                    }
                }
    }
}