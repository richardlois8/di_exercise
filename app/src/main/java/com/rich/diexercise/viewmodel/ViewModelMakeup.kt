package com.rich.diexercise.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rich.diexercise.model.ResponseItem
import com.rich.diexercise.network.RestfulAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelMakeup @Inject constructor(val api : RestfulAPI) : ViewModel() {
    var livedataAllProducts : MutableLiveData<List<ResponseItem>>

    init {
        livedataAllProducts = MutableLiveData()
    }

    fun observerAllProducts() : MutableLiveData<List<ResponseItem>>{
        return livedataAllProducts
    }

    fun callGetAllProducts(){
        api.getALlProducts().enqueue(object : retrofit2.Callback<List<ResponseItem>>{
            override fun onResponse(
                call: retrofit2.Call<List<ResponseItem>>,
                response: retrofit2.Response<List<ResponseItem>>
            ) {
                if (response.isSuccessful){
                    livedataAllProducts.postValue(response.body())
                }else{
                    livedataAllProducts.postValue(null)
                }
            }

            override fun onFailure(call: retrofit2.Call<List<ResponseItem>>, t: Throwable) {
                livedataAllProducts.postValue(null)
            }

        })
    }
}