package com.graduation.mawruth.ui.islamicmuseum.fragments.favourite

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.assets.RenderableSource
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.graduation.domain.model.pieces.PiecesResponse
import com.graduation.domain.useCase.GetPieceByARUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PiecesARViewModel @Inject constructor(
    private val getPiecesByARUseCase: GetPieceByARUseCase
) : ViewModel() {
    val piecesList = MutableLiveData<PiecesResponse?>()
    val error = MutableLiveData<Boolean>()
    fun getPiecesAR() {
        viewModelScope.launch {
            try {
                val result = getPiecesByARUseCase.invoke(49, true)
                piecesList.postValue(result)
            } catch (e: Exception) {
                error.postValue(true)
                Log.d("piecesByARError", e.message.toString())
            }
        }
    }

    fun spawnObject(
        anchor: Anchor,
        modelUri: Uri,
        context: Context,
        arFragment: ArFragment
    ) {


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