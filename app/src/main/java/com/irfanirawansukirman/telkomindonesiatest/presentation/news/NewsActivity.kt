package com.irfanirawansukirman.telkomindonesiatest.presentation.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.irfanirawansukirman.telkomindonesiatest.core.util.ViewState
import com.irfanirawansukirman.telkomindonesiatest.core.util.extension.toast
import com.irfanirawansukirman.telkomindonesiatest.databinding.ActivityNewsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * sources:
 * 1. https://blog.mindorks.com/kotlin-koin-tutorial/
 * 2. https://insert-koin.io/docs/quickstart/android
 * 3. https://github.com/HackerNews/API#new-top-and-best-stories
 */
class NewsActivity : AppCompatActivity(), NewsItemListener {

    private lateinit var viewBinding: ActivityNewsBinding

    private val viewModel by viewModel<NewsViewModel>()

    override fun onSelectedNews(newsUI: NewsUI) {
        toast(newsUI.title)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        loadObserver()
        getTopStories()

        setupViewListener()
    }

    private fun setupViewListener() {
        viewBinding.progress.setOnRefreshListener {
            viewBinding.progress.isRefreshing = false
            viewModel.getTopStories()
        }
    }

    private fun getTopStories() {
        viewModel.getTopStories()
    }

    private fun loadObserver() {
        viewModel.loader.observe(this, ::handleLoader)
        viewModel.news.observe(this, ::handleNews)
    }

    private fun handleNews(viewState: ViewState<List<NewsUI>>) {
        when (viewState) {
            is ViewState.Success -> showNews(viewState.data.orEmpty())
            is ViewState.Error -> showError(viewState.exception?.message.orEmpty())
        }
    }

    private fun handleLoader(viewState: ViewState<Nothing>) {
        when (viewState) {
            is ViewState.Loading -> showProgress()
            is ViewState.Dismiss -> hideProgress()
        }
    }

    private fun showError(message: String) {
        toast(message)
    }

    private fun showNews(news: List<NewsUI>) {
        viewBinding.rvNews.apply {
            layoutManager = LinearLayoutManager(this@NewsActivity)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = NewsAdapter(news, this@NewsActivity)
        }
    }

    private fun hideProgress() {
        viewBinding.progress.isRefreshing = false
    }

    private fun showProgress() {
        viewBinding.progress.isRefreshing = true
    }
}