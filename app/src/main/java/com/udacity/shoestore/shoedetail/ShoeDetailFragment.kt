package com.udacity.shoestore.shoedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.shoelist.ShoeViewModel

class ShoeDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        binding.shoe = Shoe()

        binding.cancelButton.setOnClickListener(View.OnClickListener {
            navigateToShoeList(it)
        })

        binding.saveButton.setOnClickListener(View.OnClickListener {
            var viewModel = ViewModelProvider(activity!!).get(ShoeViewModel::class.java)
            val shoe = binding.shoe!!
            if (shoe.name.isBlank() || shoe.company.isBlank() || shoe.size.isBlank() || shoe.description.isBlank()) {
                Toast.makeText(activity, "Fields cannot be empty ", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addShoe(binding.shoe!!)
                navigateToShoeList(it)
            }
        })
        return binding.root
    }

    fun navigateToShoeList(view: View) {
        view.findNavController()
            .navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
    }
}