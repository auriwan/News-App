package com.elthobhy.newsapp.viewmodel.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.newsapp.data.source.CatalogNewsRepository
import com.elthobhy.newsapp.data.source.local.entity.business.ArticleBusiness
import com.elthobhy.newsapp.data.source.local.entity.detik.ArticleDetik
import com.elthobhy.newsapp.data.source.local.entity.entertainment.ArticleEntertainment
import com.elthobhy.newsapp.data.source.local.entity.general.ArticleGeneral
import com.elthobhy.newsapp.data.source.local.entity.headline.ArticleHeadline
import com.elthobhy.newsapp.data.source.local.entity.health.ArticleHealth
import com.elthobhy.newsapp.data.source.local.entity.kapanlagi.ArticleKapanlagi
import com.elthobhy.newsapp.data.source.local.entity.science.ArticleScience
import com.elthobhy.newsapp.data.source.local.entity.sports.ArticleSports
import com.elthobhy.newsapp.data.source.local.entity.suara.ArticleSuara
import com.elthobhy.newsapp.data.source.local.entity.technology.ArticleTechnology
import com.elthobhy.newsapp.data.source.local.entity.viva.ArticleViva

class FavoriteViewModel(private val catalogRepo: CatalogNewsRepository) : ViewModel() {
    fun getFavoriteHeadline(): LiveData<List<ArticleHeadline>> = catalogRepo.getFavoritesHeadline()
    fun getFavoriteDetik(): LiveData<List<ArticleDetik>> = catalogRepo.getFavoritesDetik()
    fun getFavoriteViva(): LiveData<List<ArticleViva>> = catalogRepo.getFavoritesViva()
    fun getFavoriteKapanlagi(): LiveData<List<ArticleKapanlagi>> = catalogRepo.getFavoritesKapanlagi()
    fun getFavoriteSuara(): LiveData<List<ArticleSuara>> = catalogRepo.getFavoritesSuara()
    fun getFavoriteBusiness(): LiveData<List<ArticleBusiness>> = catalogRepo.getFavoritesBusiness()
    fun getFavoriteEntertainment(): LiveData<List<ArticleEntertainment>> = catalogRepo.getFavoritesEntertainment()
    fun getFavoriteGeneral(): LiveData<List<ArticleGeneral>> = catalogRepo.getFavoritesGeneral()
    fun getFavoriteHealth(): LiveData<List<ArticleHealth>> = catalogRepo.getFavoritesHealth()
    fun getFavoriteSports(): LiveData<List<ArticleSports>> = catalogRepo.getFavoritesSports()
    fun getFavoriteScience(): LiveData<List<ArticleScience>> = catalogRepo.getFavoritesScience()
    fun getFavoriteTechnology(): LiveData<List<ArticleTechnology>> = catalogRepo.getFavoritesTechnology()
}