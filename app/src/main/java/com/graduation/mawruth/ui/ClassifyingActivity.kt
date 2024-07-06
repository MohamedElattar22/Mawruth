package com.graduation.mawruth.ui


import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.graduation.mawruth.ClassifyViewModel
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivityClassifyingBinding
import com.graduation.mawruth.ui.arActivity.ObjectDataFragment
import com.graduation.mawruth.utils.typeWrite
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class ClassifyingActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityClassifyingBinding
    lateinit var viewModel: ClassifyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityClassifyingBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(viewBinding.root)
        viewModel = ViewModelProvider(this)[ClassifyViewModel::class.java]
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("uri", Uri::class.java)
        } else {
            intent.getParcelableExtra<Uri>("uri") as Uri
        }
        viewModel.getPrediction(resolveToFile(uri))
        viewBinding.smallCaptured.setImageURI(uri)
        viewBinding.capturedImage.setImageURI(uri)
        assem()
    }

    private fun resolveToFile(uri: Uri?): File {
        val fileDir = filesDir
        val file = File(fileDir, "image.jpeg")
        val inputStream = contentResolver.openInputStream(uri!!)
        val outPutStream = FileOutputStream(file)
        inputStream!!.copyTo(outPutStream)
        return file
    }

    private fun assem() {
        viewModel.responseLiveData.observe(this) {
            viewBinding.progress.isVisible = false
            viewBinding.name.isVisible = true
            viewBinding.more.isVisible = true
            viewBinding.name.typeWrite(lifecycleOwner = this, it.toString(), 100)
            viewModel.getPieceByName(it.toString(), 49)

        }
        viewModel.piecesResponse.observe(this) { pieceData ->
            val data = pieceData?.data?.get(0)
            viewBinding.more.setOnClickListener {
                intent.putExtra("pieceName", data?.name)
                intent.putExtra("pieceDes", data?.description)

            }
        }

    }

    private fun showDataSheet() {
        val dataBottomSheet = ObjectDataFragment()
        dataBottomSheet.show(supportFragmentManager, "")
    }

}