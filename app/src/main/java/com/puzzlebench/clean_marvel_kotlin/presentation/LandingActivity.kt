package com.puzzlebench.clean_marvel_kotlin.presentation

import android.animation.ObjectAnimator
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import com.puzzlebench.clean_marvel_kotlin.R
import java.lang.Exception

class LandingActivity : AppCompatActivity() {

    private var imageMarvel: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        imageMarvel = findViewById(R.id.imageView)

        rotateImage()

        val background = object : Thread(){
            override fun run() {
                super.run()

                try {
                    Thread.sleep(5000)
                    val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)

                }catch (e : Exception){
                    e.printStackTrace()

                }
            }

        }

        background.start()
    }

    private fun rotateImage() {
        val rotate = ObjectAnimator.ofFloat(imageMarvel, View.ROTATION, -360f, 0f)
        rotate.setDuration(1000)
        rotate.interpolator = AccelerateInterpolator()
        rotate.start()
    }
}
