package com.example.bookexpert.composable

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookexpert.retrofit.Product
import com.example.bookexpert.retrofit.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ApiListViewModel : ViewModel() {

    private val _transactions = MutableStateFlow<List<Product>>(emptyList())
    val transactions : StateFlow<List<Product>> = _transactions

    private val _loading = MutableStateFlow<Boolean>(true)
    val loading : StateFlow<Boolean> = _loading


    private val apiService = RetrofitClient.apiService


    fun getList(){
        viewModelScope.launch {

            val response = apiService.getList()

            if(response.isNotEmpty()){
                _loading.value = false
                _transactions.value = response
            }
        }

    }

}