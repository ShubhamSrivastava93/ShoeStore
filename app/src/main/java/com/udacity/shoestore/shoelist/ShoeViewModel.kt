package com.udacity.shoestore.shoelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {
    val mutableShoeListLiveData = MutableLiveData<List<Shoe>>()
    private val shoeList = ArrayList<Shoe>()

    fun addShoe(shoe: Shoe) {
        shoeList.add(shoe)
        mutableShoeListLiveData.value = shoeList
    }
}