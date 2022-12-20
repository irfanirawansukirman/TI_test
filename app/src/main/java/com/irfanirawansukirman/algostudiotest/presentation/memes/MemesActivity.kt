package com.irfanirawansukirman.algostudiotest.presentation.memes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.irfanirawansukirman.algostudiotest.MainApplication
import com.irfanirawansukirman.algostudiotest.core.const.Const
import com.irfanirawansukirman.algostudiotest.core.util.ViewModelFactory
import com.irfanirawansukirman.algostudiotest.core.util.ViewState
import com.irfanirawansukirman.algostudiotest.databinding.ActivityMemesBinding
import com.irfanirawansukirman.algostudiotest.presentation.memedetail.MemeDetailActivity
import com.irfanirawansukirman.algostudiotest.remote.data.response.Memes
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MemesActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMemesBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<MemesViewModel> { viewModelFactory }

    private val memesAdapter by lazy { MemesAdapter { navigateToMemeDetail(it) } }

    private fun navigateToMemeDetail(memeImageUrl: String) {
        startActivity(
            Intent(this, MemeDetailActivity::class.java)
                .putExtra(
                    Const.MEME_IMAGE_URL,
                    memeImageUrl
                )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as MainApplication).getAppComponent().inject(this)

        viewBinding = ActivityMemesBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        loadObservers()
        getMemes()

        viewBinding.rvMemes.apply {
            layoutManager = GridLayoutManager(this@MemesActivity, 3)
            adapter = memesAdapter
        }

        viewBinding.progress.apply {
            setOnRefreshListener {
                isRefreshing = false
                memesAdapter.clearAllMemes()
                getMemes()
            }
        }
    }

    private fun loadObservers() {
        viewModel.memes.observe(this, ::handleMemes)
    }

    private fun getMemes() {
        viewModel.getMemes()
    }

    private fun handleMemes(viewState: ViewState<Memes>) {
        when (viewState) {
            is ViewState.Loading -> viewBinding.progress.isRefreshing =
                !viewBinding.progress.isRefreshing
            is ViewState.Dismiss -> viewBinding.progress.isRefreshing = false
            is ViewState.Success -> memesAdapter.addAllMemes(
                viewState.data?.data?.memes ?: emptyList()
            )
            else -> Toast.makeText(this, "Please refresh the screen!", Toast.LENGTH_SHORT).show()
        }
    }
}