package com.graduation.mawruth.ui.arActivity

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.assets.RenderableSource
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode

class ARViewModel : ViewModel() {
    fun spawnObject(anchor: Anchor, modelUri: Uri, context: Context, arFragment: ArFragment) {


        val renderableSource = RenderableSource.builder()
            .setSource(context, modelUri, RenderableSource.SourceType.GLB)
            .setRecenterMode(RenderableSource.RecenterMode.ROOT)
            .setScale(0.5f)
            .build()

        ModelRenderable.builder()
            .setSource(context, renderableSource)
            .setRegistryId(modelUri)
            .build()
            .thenAccept {

                addNodeToScene(anchor, it, arFragment)


            }
            .exceptionally {
                Toast.makeText(context, "An Error Occuered", Toast.LENGTH_SHORT).show()
                null
            }


    }

    fun addNodeToScene(
        anchor: Anchor,
        modelRenderable: ModelRenderable,
        arFragment: ArFragment
    ) {
        val anchorNode = AnchorNode(anchor)
        TransformableNode(arFragment.transformationSystem).apply {
            renderable = modelRenderable
            setParent(anchorNode)
        }
        arFragment.arSceneView.scene.addChild(anchorNode)

    }
}