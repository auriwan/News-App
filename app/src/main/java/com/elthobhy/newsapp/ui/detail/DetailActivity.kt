package com.elthobhy.newsapp.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.browser.customtabs.CustomTabsIntent
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elthobhy.newsapp.R
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
import com.elthobhy.newsapp.databinding.ActivityDetailBinding
import com.elthobhy.newsapp.utils.Constants
import com.elthobhy.newsapp.viewmodel.detail.DetailViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel by inject<DetailViewModel>()
    private val listKey = arrayListOf(
        Constants.BUSINESS,
        Constants.ENTERTAINMENT,
        Constants.GENERAL,
        Constants.HEALTH,
        Constants.SCIENCE,
        Constants.SPORTS,
        Constants.TECHNOLOGY,
        Constants.TOP_HEADLINE,
        Constants.DETIK,
        Constants.VIVA,
        Constants.KAPAN_LAGI,
        Constants.SUARA
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        for (key in listKey) {
            showDetail(key)
        }

    }

    private fun onClick(url: String?) {
        binding.goToLink.setOnClickListener {
            val intent = CustomTabsIntent.Builder().build()
            intent.launchUrl(this,Uri.parse(url))
        }

    }

    private fun setActionButton(
        health: ArticleHealth?,
        headline: ArticleHeadline?,
        business: ArticleBusiness?,
        entertainment: ArticleEntertainment?,
        general: ArticleGeneral?,
        science: ArticleScience?,
        sports: ArticleSports?,
        technology: ArticleTechnology?,
        viva: ArticleViva?,
        detik: ArticleDetik?,
        kapanlagi: ArticleKapanlagi?,
        suara: ArticleSuara?,
    ) {
        binding.bookmark.setOnClickListener {
            setBookmark(
                health,
                headline,
                business,
                entertainment,
                general,
                science,
                sports,
                technology,
                viva,
                detik,
                kapanlagi,
                suara
            )
        }
    }

    private fun setBookmark(
        health: ArticleHealth?,
        headline: ArticleHeadline?,
        business: ArticleBusiness?,
        entertainment: ArticleEntertainment?,
        general: ArticleGeneral?,
        science: ArticleScience?,
        sports: ArticleSports?,
        technology: ArticleTechnology?,
        viva: ArticleViva?,
        detik: ArticleDetik?,
        kapanlagi: ArticleKapanlagi?,
        suara: ArticleSuara?
    ) {

            if(health != null)  {
                if (health.bookmarked) {
                    showSnackBar("${health.source} Removed from favorite")
                } else {
                    showSnackBar("${health.source} Added to favorite")
                }
                detailViewModel.setBookmarkedHealth(health)
            }
            else if(headline!= null)  {
                if (headline.bookmarked) {
                    showSnackBar("${headline.title} Removed from favorite")
                } else {
                    showSnackBar("${headline.title} Added to favorite")
                }
                detailViewModel.setBookmarkedHeadline(headline)
            }
            else if(business != null) {
                if (business.bookmarked) {
                    showSnackBar("${business.source} Removed from favorite")
                } else {
                    showSnackBar("${business.source} Added to favorite")
                }
                detailViewModel.setBookmarkedBusiness(business)
            }
            else if(entertainment!= null)  {
                if (entertainment.bookmarked) {
                    showSnackBar("${entertainment.title} Removed from favorite")
                } else {
                    showSnackBar("${entertainment.title} Added to favorite")
                }
                detailViewModel.setBookmarkedEntertainment(entertainment)
            }
            else if(general != null) {
                if (general.bookmarked) {
                    showSnackBar("${general.title} Removed from favorite")
                } else {
                    showSnackBar("${general.title} Added to favorite")
                }
                detailViewModel.setBookmarkedGeneral(general)
            }
            else if(science!= null)  {
                if (science.bookmarked) {
                    showSnackBar("${science.title} Removed from favorite")
                } else {
                    showSnackBar("${science.title} Added to favorite")
                }
                detailViewModel.setBookmarkedScience(science)
            }
            else if(sports != null) {
                if (sports.bookmarked) {
                    showSnackBar("${sports.title} Removed from favorite")
                } else {
                    showSnackBar("${sports.title} Added to favorite")
                }
                detailViewModel.setBookmarkedSports(sports)
            }
            else if(technology != null) {
                if (technology.bookmarked) {
                    showSnackBar("${technology.title} Removed from favorite")
                } else {
                    showSnackBar("${technology.title} Added to favorite")
                }
                detailViewModel.setBookmarkedTechnology(technology)
            }
            else if(viva != null) {
                if (viva.bookmarked) {
                    showSnackBar("${viva.title} Removed from favorite")
                } else {
                    showSnackBar("${viva.title} Added to favorite")
                }
                detailViewModel.setBookmarkedViva(viva)
            }
            else if(detik != null) {
                if (detik.bookmarked) {
                    showSnackBar("${detik.title} Removed from favorite")
                } else {
                    showSnackBar("${detik.title} Added to favorite")
                }
                detailViewModel.setBookmarkedDetik(detik)
            }
            else if(kapanlagi != null) {
                if (kapanlagi.bookmarked) {
                    showSnackBar("${kapanlagi.title} Removed from favorite")
                } else {
                    showSnackBar("${kapanlagi.title} Added to favorite")
                }
                detailViewModel.setBookmarkedKapanlagi(kapanlagi)
            }
            else if(suara != null) {
                if (suara.bookmarked) {
                    showSnackBar("${suara.title} Removed from favorite")
                } else {
                    showSnackBar("${suara.title} Added to favorite")
                }
                detailViewModel.setBookmarkedSuara(suara)
            }
            else{
                Log.e("bookmark", "setBookmark: semua null", )
            }
    }
    private fun showSnackBar(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG)
            .show()
    }

    private fun setBookmarkedState(isFavorite: Boolean) {
        if (isFavorite) {
            binding.bookmark.setImageResource(R.drawable.ic_baseline_bookmark_24)
        } else {
            binding.bookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
        }
    }

    private fun showDetail(key: String) {
        when (key) {
            Constants.BUSINESS -> {
                val dataIntent = intent?.getParcelableExtra<ArticleBusiness>(Constants.BUSINESS)
                dataIntent?.title?.let { data ->
                    detailViewModel.getBusinessNews(data).observe(this){
                        setActionButton(business = it ,
                            health = null, entertainment = null,
                            headline = null, general = null, science = null,
                            sports = null, technology = null, viva = null, detik = null,
                            kapanlagi = null, suara = null
                        )
                        displayDetail(it)
                    }
                }
            }
            Constants.ENTERTAINMENT -> {
                val dataIntent = intent?.getParcelableExtra<ArticleEntertainment>(Constants.ENTERTAINMENT)
                dataIntent?.title?.let { data ->
                    detailViewModel.getEntertainment(data).observe(this){
                        displayDetail(it)
                        setActionButton(entertainment = it,business = null,
                            health = null,
                            headline = null, general = null, science = null,
                            sports = null, technology = null, viva = null, detik = null,
                            kapanlagi = null, suara = null)
                    }
                }

            }
            Constants.GENERAL -> {
                val dataIntent = intent?.getParcelableExtra<ArticleGeneral>(Constants.GENERAL)
                dataIntent?.title?.let { data ->
                    detailViewModel.getGeneralNews(data).observe(this){
                        displayDetail(it)
                        setActionButton(general = it,science = null,
                            sports = null, technology = null, viva = null, detik = null,
                            kapanlagi = null, suara = null,entertainment = null,business = null,
                            health = null,
                            headline = null,)
                    }
                }
            }
            Constants.HEALTH -> {
                val dataIntent = intent?.getParcelableExtra<ArticleHealth>(Constants.HEALTH)
                dataIntent?.title?.let { data ->
                    detailViewModel.getHealthNews(data).observe(this){
                        displayDetail(it)
                        setActionButton(general = null,science = null,
                            sports = null, technology = null, viva = null, detik = null,
                            kapanlagi = null, suara = null,entertainment = null,business = null,
                            health = it,
                            headline = null,)
                    }
                }
            }
            Constants.SCIENCE -> {
                val dataIntent = intent?.getParcelableExtra<ArticleScience>(Constants.SCIENCE)
                dataIntent?.title?.let { data ->
                    detailViewModel.getScienceNews(data).observe(this){
                        displayDetail(it)
                        setActionButton(general = null,science = it,
                            sports = null, technology = null, viva = null, detik = null,
                            kapanlagi = null, suara = null,entertainment = null,business = null,
                            health = null,
                            headline = null,)
                    }
                }
            }
            Constants.SPORTS -> {
                val dataIntent = intent?.getParcelableExtra<ArticleSports>(Constants.SPORTS)
                dataIntent?.title?.let { data ->
                    detailViewModel.getSportsNews(data).observe(this){
                        displayDetail(it)
                        setActionButton(general = null,science = null,
                            sports = it, technology = null, viva = null, detik = null,
                            kapanlagi = null, suara = null,entertainment = null,business = null,
                            health = null,
                            headline = null,)
                    }
                }

            }
            Constants.TECHNOLOGY -> {
                val dataIntent = intent?.getParcelableExtra<ArticleTechnology>(Constants.TECHNOLOGY)
                dataIntent?.title?.let { data ->
                    detailViewModel.getTechnologyNews(data).observe(this){
                        displayDetail(it)
                        setActionButton(general = null,science = null,
                            sports = null, technology = it, viva = null, detik = null,
                            kapanlagi = null, suara = null,entertainment = null,business = null,
                            health = null,
                            headline = null,)
                    }
                }

            }
            Constants.VIVA -> {
                val dataIntent = intent?.getParcelableExtra<ArticleViva>(Constants.VIVA)
                dataIntent?.title?.let { data ->
                    detailViewModel.getVivaNews(data).observe(this){
                        displayDetail(it)
                        setActionButton(general = null,science = null,
                            sports = null, technology = null, viva = it, detik = null,
                            kapanlagi = null, suara = null,entertainment = null,business = null,
                            health = null,
                            headline = null,)
                    }
                }


            }
            Constants.SUARA -> {
                val dataIntent = intent?.getParcelableExtra<ArticleSuara>(Constants.SUARA)
                dataIntent?.title?.let { data ->
                    detailViewModel.getSuaraNews(data).observe(this){
                        displayDetail(it)
                        setActionButton(general = null,science = null,
                            sports = null, technology = null, viva = null, detik = null,
                            kapanlagi = null, suara = it,entertainment = null,business = null,
                            health = null,
                            headline = null,)
                    }
                }


            }
            Constants.DETIK -> {
                val data = intent?.getParcelableExtra<ArticleDetik>(Constants.DETIK)
                data?.title?.let { s ->
                    detailViewModel.getDetikNews(s).observe(this){
                        displayDetail(it)
                        setActionButton(general = null,science = null,
                            sports = null, technology = null, viva = null, detik = it,
                            kapanlagi = null, suara = null,entertainment = null,business = null,
                            health = null,
                            headline = null,)
                    }
                }


            }
            Constants.KAPAN_LAGI -> {
                val dataIntent = intent?.getParcelableExtra<ArticleKapanlagi>(Constants.KAPAN_LAGI)
                dataIntent?.title?.let { data ->
                    detailViewModel.getKapanlagiNews(data).observe(this){
                        displayDetail(it)
                        setActionButton(general = null,science = null,
                            sports = null, technology = null, viva = null, detik = null,
                            kapanlagi = it, suara = null,entertainment = null,business = null,
                            health = null,
                            headline = null,)
                    }
                }


            }
            Constants.TOP_HEADLINE -> {
                val dataIntent = intent?.getParcelableExtra<ArticleHeadline>(Constants.TOP_HEADLINE)
                dataIntent?.title?.let { data ->
                    detailViewModel.getHeadline(data).observe(this){
                        displayDetail(it)
                        setActionButton(general = null,science = null,
                            sports = null, technology = null, viva = null, detik = null,
                            kapanlagi = null, suara = null,entertainment = null,business = null,
                            health = null,
                            headline = it,)
                    }
                }


            }
        }
    }


    private fun displayDetail(article: Parcelable?) {
        when (article) {
            is ArticleKapanlagi -> {
                binding.apply {
                    if(article.urlToImage.isNullOrEmpty()){
                        imageNews.visibility=View.GONE
                    }else{
                        Glide.with(this@DetailActivity)
                            .load(article.urlToImage)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .placeholder(R.drawable.ic_baseline_broken_image_24)
                            .into(imageNews)
                    }

                    titleNews.text = article.title
                    sourceNews.text = article.source?.name
                    dateNews.text = article.publishedAt
                    contentNews.text = article.content
                    onClick(article.url)
                    setBookmarkedState(article.bookmarked)
                }
            }
            is ArticleSuara -> {
                binding.apply {
                    if(article.urlToImage.isNullOrEmpty()){
                        imageNews.visibility=View.GONE
                    }else{
                        Glide.with(this@DetailActivity)
                            .load(article.urlToImage)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .placeholder(R.drawable.ic_baseline_broken_image_24)
                            .into(imageNews)
                    }

                    titleNews.text = article.title
                    sourceNews.text = article.source?.name
                    dateNews.text = article.publishedAt
                    contentNews.text = article.content
                    onClick(article.url)
                    setBookmarkedState(article.bookmarked)
                }
            }
            is ArticleDetik -> {
                binding.apply {
                    if(article.urlToImage.isNullOrEmpty()){
                        imageNews.visibility=View.GONE
                    }else{
                        Glide.with(this@DetailActivity)
                            .load(article.urlToImage)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .placeholder(R.drawable.ic_baseline_broken_image_24)
                            .into(imageNews)
                    }

                    titleNews.text = article.title
                    sourceNews.text = article.source?.name
                    dateNews.text = article.publishedAt
                    contentNews.text = article.content
                    onClick(article.url)
                    setBookmarkedState(article.bookmarked)
                }
            }
            is ArticleViva -> {
                binding.apply {
                    if(article.urlToImage.isNullOrEmpty()){
                        imageNews.visibility=View.GONE
                    }else{
                        Glide.with(this@DetailActivity)
                            .load(article.urlToImage)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .placeholder(R.drawable.ic_baseline_broken_image_24)
                            .into(imageNews)
                    }

                    titleNews.text = article.title
                    sourceNews.text = article.source?.name
                    dateNews.text = article.publishedAt
                    contentNews.text = article.content
                    onClick(article.url)
                    setBookmarkedState(article.bookmarked)
                }
            }
            is ArticleTechnology -> {
                binding.apply {
                    if(article.urlToImage.isNullOrEmpty()){
                        imageNews.visibility=View.GONE
                    }else{
                        Glide.with(this@DetailActivity)
                            .load(article.urlToImage)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .placeholder(R.drawable.ic_baseline_broken_image_24)
                            .into(imageNews)
                    }

                    titleNews.text = article.title
                    sourceNews.text = article.source?.name
                    dateNews.text = article.publishedAt
                    contentNews.text = article.content
                    onClick(article.url)
                    setBookmarkedState(article.bookmarked)
                }
            }
            is ArticleSports -> {
                binding.apply {
                    if(article.urlToImage.isNullOrEmpty()){
                        imageNews.visibility=View.GONE
                    }else{
                        Glide.with(this@DetailActivity)
                            .load(article.urlToImage)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .placeholder(R.drawable.ic_baseline_broken_image_24)
                            .into(imageNews)
                    }

                    titleNews.text = article.title
                    sourceNews.text = article.source?.name
                    dateNews.text = article.publishedAt
                    contentNews.text = article.content
                    onClick(article.url)
                    setBookmarkedState(article.bookmarked)
                }
            }
            is ArticleScience -> {
                binding.apply {
                    if(article.urlToImage.isNullOrEmpty()){
                        imageNews.visibility=View.GONE
                    }else{
                        Glide.with(this@DetailActivity)
                            .load(article.urlToImage)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .placeholder(R.drawable.ic_baseline_broken_image_24)
                            .into(imageNews)
                    }

                    titleNews.text = article.title
                    sourceNews.text = article.source?.name
                    dateNews.text = article.publishedAt
                    contentNews.text = article.content
                    onClick(article.url)
                    setBookmarkedState(article.bookmarked)
                }
            }
            is ArticleGeneral -> {
                binding.apply {
                    if(article.urlToImage.isNullOrEmpty()){
                        imageNews.visibility=View.GONE
                    }else{
                        Glide.with(this@DetailActivity)
                            .load(article.urlToImage)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .placeholder(R.drawable.ic_baseline_broken_image_24)
                            .into(imageNews)
                    }

                    titleNews.text = article.title
                    sourceNews.text = article.source?.name
                    dateNews.text = article.publishedAt
                    contentNews.text = article.content
                    onClick(article.url)
                    setBookmarkedState(article.bookmarked)
                }
            }
            is ArticleEntertainment -> {
                binding.apply {
                    if(article.urlToImage.isNullOrEmpty()){
                        imageNews.visibility=View.GONE
                    }else{
                        Glide.with(this@DetailActivity)
                            .load(article.urlToImage)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .placeholder(R.drawable.ic_baseline_broken_image_24)
                            .into(imageNews)
                    }

                    titleNews.text = article.title
                    sourceNews.text = article.source?.name
                    dateNews.text = article.publishedAt
                    contentNews.text = article.content
                    onClick(article.url)
                    setBookmarkedState(article.bookmarked)
                }
            }
            is ArticleBusiness -> {
                binding.apply {
                    if(article.urlToImage.isNullOrEmpty()){
                        imageNews.visibility=View.GONE
                    }else{
                        Glide.with(this@DetailActivity)
                            .load(article.urlToImage)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .placeholder(R.drawable.ic_baseline_broken_image_24)
                            .into(imageNews)
                    }

                    titleNews.text = article.title
                    sourceNews.text = article.source?.name
                    dateNews.text = article.publishedAt
                    contentNews.text = article.content
                    onClick(article.url)
                    setBookmarkedState(article.bookmarked)
                }
            }
            is ArticleHeadline -> {
                binding.apply {
                    if(article.urlToImage.isNullOrEmpty()){
                        lottie.visibility=View.VISIBLE
                        imageNews.visibility=View.GONE
                    }else{
                        lottie.visibility=View.GONE
                        imageNews.visibility=View.VISIBLE
                        Glide.with(this@DetailActivity)
                            .load(article.urlToImage)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .placeholder(R.drawable.ic_baseline_broken_image_24)
                            .into(imageNews)
                    }

                    titleNews.text = article.title
                    sourceNews.text = article.source?.name
                    val secondApiFormat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                    } else {
                        TODO("VERSION.SDK_INT < O")
                    }
                    val time = LocalDate.parse(article.publishedAt, secondApiFormat)
                    dateNews.text = time.toString()
                    contentNews.text = article.content
                    onClick(article.url)
                    setBookmarkedState(article.bookmarked)
                }
            }
            is ArticleHealth -> {
                binding.apply {
                    if(article.urlToImage.isNullOrEmpty()){
                        imageNews.visibility=View.GONE
                    }else{
                        Glide.with(this@DetailActivity)
                            .load(article.urlToImage)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .placeholder(R.drawable.ic_baseline_broken_image_24)
                            .into(imageNews)
                    }

                    titleNews.text = article.title
                    sourceNews.text = article.source?.name
                    dateNews.text = article.publishedAt
                    contentNews.text = article.content
                    onClick(article.url)
                    setBookmarkedState(article.bookmarked)
                }
            }

        }


    }


}