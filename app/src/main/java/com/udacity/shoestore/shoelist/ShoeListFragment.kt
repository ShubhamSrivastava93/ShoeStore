package com.udacity.shoestore.shoelist

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe


class ShoeListFragment : Fragment() {

    private lateinit var viewModel: ShoeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        binding.detailFab.setOnClickListener(View.OnClickListener {
            it.findNavController()
                .navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        })

        viewModel = ViewModelProvider(activity!!).get(ShoeViewModel::class.java)

        viewModel.mutableShoeListLiveData.observe(viewLifecycleOwner, Observer {
            it.forEach { shoe: Shoe ->
                addViewsForShoeList(binding.root, shoe)
            }
        })
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun addViewsForShoeList(shoeListView: View, shoe: Shoe) {
        val childListView =
            LayoutInflater.from(context).inflate(R.layout.shoe_list_child, null, false)
        val tvName = childListView.findViewById(R.id.name_text) as TextView
        val tvCompany = childListView.findViewById(R.id.company_text) as TextView
        val tvSize = childListView.findViewById(R.id.size_text) as TextView
        val tvDescription = childListView.findViewById(R.id.description_text) as TextView

        tvName.text = getString(R.string.name_format, shoe.name)
        tvCompany.text = getString(R.string.company_format, shoe.company)
        tvSize.text = getString(R.string.size_format, shoe.size)
        tvDescription.text = getString(R.string.description_format, shoe.description)

        val linearLayout = shoeListView.findViewById(R.id.linear_layout) as LinearLayout

        linearLayout.addView(childListView)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            view!!.findNavController()
        ) || super.onOptionsItemSelected(item)
    }
}