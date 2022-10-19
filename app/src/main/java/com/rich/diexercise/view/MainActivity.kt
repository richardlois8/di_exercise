package com.rich.diexercise.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rich.diexercise.databinding.ActivityMainBinding
import com.rich.diexercise.view.adapter.MakeupAdapter
import com.rich.diexercise.viewmodel.ViewModelMakeup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var makeupVM : ViewModelMakeup
    lateinit var adapter : MakeupAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        makeupVM = ViewModelProvider(this).get(ViewModelMakeup::class.java)
        setRecyclerview()
    }

    private fun setRecyclerview() {
        makeupVM.callGetAllProducts()
        makeupVM.observerAllProducts().observe(this){
            showLoading(true)
            adapter = MakeupAdapter(it)
            if(it != null){
                showLoading(false)
                binding.apply {
                    recView.layoutManager = LinearLayoutManager(this@MainActivity)
                    recView.adapter = adapter
                }
            }
        }
    }

    private fun showLoading(status : Boolean){
        if(status){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}