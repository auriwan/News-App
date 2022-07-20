package com.elthobhy.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.newsapp.data.source.CatalogNewsRepository
import com.elthobhy.newsapp.data.source.local.entity.Article

class KapanlagiViewModel(private val catalogRepo:CatalogNewsRepository):ViewModel() {
    fun getKapanlagiNews(): LiveData<List<Article>> =
        catalogRepo.getKapanlagiNews()
}