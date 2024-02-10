package com.graduation.mawruth.ui.arActivity

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.assets.RenderableSource
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivityAgumentedRealityBinding
import com.graduation.mawruth.utils.remoteObjects

class AgumentedRealityActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityAgumentedRealityBinding
    private lateinit var arFragment: ArFragment
    private val url = remoteObjects.sourceURL
    private val fromBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.from_bottom_anim
        )
    }
    private val toBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.to_bottom_anim
        )
    }
    private var clicked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityAgumentedRealityBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
    }

    private fun initViews() {
        arFragment = supportFragmentManager.findFragmentById(R.id.arFragment) as ArFragment
        arFragment.setOnTapArPlaneListener { hitResult, _, _ ->

            spawnObject(hitResult.createAnchor(), Uri.parse(url))
        }
        viewBinding.voiceBtn.setOnClickListener {
            Toast.makeText(this, "voice opened", Toast.LENGTH_SHORT).show()
        }
        viewBinding.dataBtn.setOnClickListener {
            showDataSheet()
        }
        viewBinding.fab.setOnClickListener {
            onMainButtonClick()

        }

    }

    private fun onMainButtonClick() {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            viewBinding.voiceBtn.visibility = View.VISIBLE
            viewBinding.dataBtn.visibility = View.VISIBLE
        } else {
            viewBinding.voiceBtn.visibility = View.INVISIBLE
            viewBinding.dataBtn.visibility = View.INVISIBLE
        }

    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked) {
            viewBinding.voiceBtn.startAnimation(fromBottom)
            viewBinding.dataBtn.startAnimation(fromBottom)
        } else {
            viewBinding.voiceBtn.startAnimation(toBottom)
            viewBinding.dataBtn.startAnimation(toBottom)

        }
    }

    private fun showDataSheet() {
        val dataBottomSheet = ObjectDataFragment()
        dataBottomSheet.show(supportFragmentManager, "")
    }

    fun spawnObject(anchor: Anchor, modelUri: Uri) {

        val renderableSource = RenderableSource.builder()
            .setSource(this, modelUri, RenderableSource.SourceType.GLB)
            .setRecenterMode(RenderableSource.RecenterMode.ROOT)
            .build()
        ModelRenderable.builder()
            .setSource(this, renderableSource)
            .setRegistryId(modelUri)
            .build()
            .thenAccept {
                addNodeToScene(anchor, it)
            }
            .exceptionally {
                Toast.makeText(this, "An Error Occuered", Toast.LENGTH_SHORT).show()
                null
            }
    }

    private fun addNodeToScene(anchor: Anchor, modelRenderable: ModelRenderable) {
        val anchorNode = AnchorNode(anchor)
        TransformableNode(arFragment.transformationSystem).apply {
            renderable = modelRenderable
            setParent(anchorNode)

        }
        arFragment.arSceneView.scene.addChild(anchorNode)

    }

}