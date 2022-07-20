package com.elthobhy.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import com.elthobhy.newsapp.data.source.local.entity.Article
import com.elthobhy.newsapp.utils.DataDummy

class HeadlineViewModel: ViewModel() {
    fun getHeadline(): List<Article> =
        DataDummy.generateDummyHeadline()
}