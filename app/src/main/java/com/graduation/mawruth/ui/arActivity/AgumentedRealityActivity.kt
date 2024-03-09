package com.graduation.mawruth.ui.arActivity

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.assets.RenderableSource
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivityAgumentedRealityBinding

class AgumentedRealityActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityAgumentedRealityBinding
    private lateinit var arFragment: ArFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityAgumentedRealityBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
    }

    private fun initViews() {

        arFragment = supportFragmentManager.findFragmentById(R.id.arFragment) as ArFragment
        val arData = intent.getStringExtra("agmunted").toString()
        val pieceName = intent.getStringExtra("pieceName")
        Log.i("pieceAr", arData)
        Log.i("pieceData", pieceName.toString())
        arFragment.setOnTapArPlaneListener { hitResult, _, _ ->
                spawnObject(hitResult.createAnchor(), Uri.parse(arData))
        }

        viewBinding.dataBtn.setOnClickListener {
            Toast.makeText(this, "voice opened", Toast.LENGTH_SHORT).show()
        }
        viewBinding.fab.setOnClickListener {
            showDataSheet()
        }

    }


    private fun showDataSheet() {
        val dataBottomSheet = ObjectDataFragment()
        dataBottomSheet.show(supportFragmentManager, "")
    }


    private fun spawnObject(anchor: Anchor, modelUri: Uri) {


            val renderableSource = RenderableSource.builder()
                .setSource(this, modelUri, RenderableSource.SourceType.GLB)
                .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                .setScale(0.5f)
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