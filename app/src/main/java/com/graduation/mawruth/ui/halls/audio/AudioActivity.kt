package com.graduation.mawruth.ui.halls.audio

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.graduation.mawruth.R
import com.graduation.mawruth.databinding.ActivityAudioBinding
import com.graduation.mawruth.ui.halls.IslamicMuseumHallsActivity

class AudioActivity : AppCompatActivity() {
    lateinit var binding: ActivityAudioBinding
    private lateinit var dialog: Dialog
    lateinit var player: SimpleExoPlayer
    var duration: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        binding = ActivityAudioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrieveData()
        createDialog()
        binding.backBtn.setOnClickListener {
            navigateToHalls()
        }
        player = SimpleExoPlayer.Builder(this@AudioActivity).build()
        player.addListener(object : Player.Listener {
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                if (playbackState == Player.STATE_READY && player.playWhenReady) {
                    binding.playPauseButton.setImageDrawable(resources.getDrawable(R.drawable.pause_btn))
                } else {
                    binding.playPauseButton.setImageDrawable(resources.getDrawable(R.drawable.play_btn))
                }
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                duration = player.duration.toInt() / 1000
                binding.seekbar.max = duration
                binding.time.text = gettimeString(duration)
            }

            override fun onPositionDiscontinuity(
                oldPosition: Player.PositionInfo,
                newPosition: Player.PositionInfo,
                reason: Int
            ) {
                var currentposition = player.currentPosition.toInt() / 1000
                binding.seekbar.progress = currentposition
                binding.time.text = gettimeString(player.duration.toInt() / 1000)
            }

        })

        val soundPath =
            "https://mawruth.blob.core.windows.net/mawruth/${intent.getStringExtra("sound")}"
        Log.d("soundPath", soundPath.toString())
        var mediaItem = MediaItem.fromUri(soundPath)

        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()

        binding.playPauseButton.setOnClickListener {
            player.playWhenReady = !player.playWhenReady
        }
        binding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    player.seekTo(progress.toLong() * 1000)
                    binding.time.text = gettimeString(duration)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

        var handler = Handler(Looper.getMainLooper())
        handler.post(object : Runnable {
            override fun run() {
                var currentposition = player.currentPosition.toInt() / 1000
                binding.seekbar.progress = currentposition
                binding.timeSeek.text = gettimeString(currentposition)
                handler.postDelayed(this, 1000)
            }

        })

        player.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (Player.STATE_BUFFERING == playbackState) {
                    dialog.show()
                } else {
                    dialog.hide()
                }
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        player.stop()
        finish()
    }
    fun gettimeString(duration: Int): String {
        val min = duration / 60
        val sec = duration % 60
        val time = String.format("%02d:%02d", min, sec)
        return time
    }

    private fun createDialog() {
        dialog = Dialog(this)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        val dialogView = LayoutInflater.from(this).inflate(R.layout.loading_dialog, null)
        dialog.setContentView(dialogView)
    }
    private fun retrieveData() {
        val text = intent.getStringExtra("name")
        val image = intent.getStringExtra("image").toString()
        Log.d("image", image.toString())
        Glide.with(this)
            .load(image)
            .into(binding.audioImage)
        binding.audioText.text = text
    }

    private fun navigateToHalls() {
        val intent = Intent(this, IslamicMuseumHallsActivity::class.java)
        startActivity(intent)
        finish()
    }



}