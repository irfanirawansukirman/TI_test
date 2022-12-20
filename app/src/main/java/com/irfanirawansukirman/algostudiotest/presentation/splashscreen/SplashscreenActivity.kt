package com.irfanirawansukirman.algostudiotest.presentation.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.irfanirawansukirman.algostudiotest.databinding.ActivitySplashscreenBinding
import com.irfanirawansukirman.algostudiotest.presentation.memes.MemesActivity

class SplashscreenActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySplashscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        startActivity(Intent(this, MemesActivity::class.java))
    }
}