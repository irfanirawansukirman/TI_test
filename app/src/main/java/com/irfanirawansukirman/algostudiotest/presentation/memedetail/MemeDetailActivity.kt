package com.irfanirawansukirman.algostudiotest.presentation.memedetail

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.net.Uri
import android.os.*
import android.provider.MediaStore
import android.view.PixelCopy
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import coil.load
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.irfanirawansukirman.algostudiotest.core.const.Const
import com.irfanirawansukirman.algostudiotest.core.util.ImageUtil
import com.irfanirawansukirman.algostudiotest.databinding.ActivityMemeDetailBinding
import com.permissionx.guolindev.PermissionX
import id.zelory.compressor.Compressor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


// source: https://stackoverflow.com/a/58315279
// source: https://www.simplifiedcoding.net/android-save-bitmap-to-gallery/
class MemeDetailActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMemeDetailBinding

    private var hasLogo = false
    private var hasText = false
    private var selectedBitmap: Bitmap? = null

    private val bothFileConfig by lazy {
        ImagePickerConfig {
            mode = ImagePickerMode.SINGLE // default is multi image mode
            limit = 1 // max images can be selected (99 by default)
            returnMode = ReturnMode.ALL
        }
    }

    private val logoLauncher = registerImagePicker { items ->
        if (items.isNotEmpty()) {
            val image = items[0]
            val logo = ImageUtil.getFile(this, image.uri).compressIt(this)
            viewBinding.ivLogo.load(logo)

            hasLogo = true

            showButton()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMemeDetailBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.tvLogo.setOnClickListener { pickLogo() }
        viewBinding.ivBack.setOnClickListener { finish() }
        viewBinding.tvText.setOnClickListener { addText() }
        viewBinding.btnSave.setOnClickListener { saveImage() }
        viewBinding.btnShare.setOnClickListener {
            lifecycleScope.launch {
                saveImage()
                delay(1_000)
                selectedBitmap?.let { image -> shareBitmap(image) }
            }
        }

        val memeImageUrl = intent?.getStringExtra(Const.MEME_IMAGE_URL)
        viewBinding.ivBackground.load(memeImageUrl)
    }

    private fun saveImage() {
        captureView(viewBinding.clContainer) {
            selectedBitmap = it
            PermissionX.init(this).permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .request { allGranted, grantedList, deniedList ->
                    if (allGranted) saveBitmapToStorage(it)
                }
        }
    }


    private fun shareBitmap(bitmap: Bitmap) {
        val bitmapPath =
            MediaStore.Images.Media.insertImage(contentResolver, bitmap, "Share", "Share Meme")
        val bitmapUri = Uri.parse(bitmapPath)
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/png"
        intent.putExtra(Intent.EXTRA_STREAM, bitmapUri)
        startActivity(Intent.createChooser(intent, "Share"))
    }

    private fun showButton() {
        viewBinding.apply {
            btnSave.isVisible = hasLogo && hasText
            btnShare.isVisible = hasLogo && hasText

            tvLogo.isVisible = !(hasLogo && hasText)
            tvText.isVisible = !(hasLogo && hasText)
        }
    }

    private fun addText() {
        hasText = true
        viewBinding.tvTitle.text = "Lorem Ipsum Dolor Sit Amet"
        viewBinding.tvSubTitle.text = "Lorem Ipsum Dolor Sit Amet"

        showButton()
    }

    private fun pickLogo() {
        PermissionX.init(this).permissions(Manifest.permission.READ_EXTERNAL_STORAGE)
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) logoLauncher.launch(bothFileConfig)
            }
    }

    private fun File.compressIt(context: Context): File {
        return try {
            Compressor(context).compressToFile(this)
        } catch (e: Exception) {
            // FirebaseCrashlytics.getInstance().recordException(e)
            this
        }
    }

    private fun saveBitmapToStorage(bitmap: Bitmap) {
        //Generating a file name
        val filename = "${System.currentTimeMillis()}.jpg"

        //Output stream
        var fos: OutputStream? = null

        //For devices running android >= Q
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            //getting the contentResolver
            contentResolver?.also { resolver ->

                //Content resolver will process the contentvalues
                val contentValues = ContentValues().apply {

                    //putting file information in content values
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                //Inserting the contentValues to contentResolver and getting the Uri
                val imageUri: Uri? =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                //Opening an outputstream with the Uri that we got
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            //These for devices running on android < Q
            //So I don't think an explanation is needed here
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }

        fos?.use {
            //Finally writing the bitmap to the output stream that we opened
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(this, "Saved to Photos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun captureView(view: View, bitmapCallback: (Bitmap) -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Above Android O, use PixelCopy
            val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val location = IntArray(2)
            view.getLocationInWindow(location)
            PixelCopy.request(
                window,
                Rect(location[0], location[1], location[0] + view.width, location[1] + view.height),
                bitmap,
                {
                    if (it == PixelCopy.SUCCESS) {
                        bitmapCallback.invoke(bitmap)
                    }
                },
                Handler(Looper.getMainLooper())
            )
        } else {
            val tBitmap = Bitmap.createBitmap(
                view.width, view.height, Bitmap.Config.RGB_565
            )
            val canvas = Canvas(tBitmap)
            view.draw(canvas)
            canvas.setBitmap(null)
            bitmapCallback.invoke(tBitmap)
        }
    }
}