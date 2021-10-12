package com.example.spotlight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import android.widget.Toast
import com.example.spotlight.databinding.ActivityMainBinding
import com.takusemba.spotlight.OnSpotlightListener
import com.takusemba.spotlight.OnTargetListener
import com.takusemba.spotlight.Spotlight
import com.takusemba.spotlight.shape.Circle
import com.takusemba.spotlight.Target
import com.takusemba.spotlight.shape.RoundedRectangle

class MainActivity : AppCompatActivity() {

    private lateinit var _binding : ActivityMainBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //this is very imp. as views should be created first then only spotlight can function
        binding.start.setOnClickListener {

            val targets = ArrayList<Target>()

            // first target
            val firstRoot = FrameLayout(this)
            val first = layoutInflater.inflate(R.layout.layout_target, firstRoot)
            val firstTarget = Target.Builder()
                .setAnchor(binding.one)
                .setShape(Circle(100f))
                .setOverlay(first)
                .setOnTargetListener(object : OnTargetListener {
                    override fun onStarted() {
                        Toast.makeText(
                            this@MainActivity,
                            "first target is started",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onEnded() {
                        Toast.makeText(
                            this@MainActivity,
                            "first target is ended",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                })
                .build()

            targets.add(firstTarget)

            // second target
            val secondRoot = FrameLayout(this)
            val second = layoutInflater.inflate(R.layout.layout_target, secondRoot)
            val secondTarget = Target.Builder()
                .setAnchor(binding.two)
                .setShape(Circle(150f))
                .setOverlay(second)
                .setOnTargetListener(object : OnTargetListener {
                    override fun onStarted() {
                        Toast.makeText(
                            this@MainActivity,
                            "second target is started",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onEnded() {
                        Toast.makeText(
                            this@MainActivity,
                            "second target is ended",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
                .build()

            targets.add(secondTarget)

            // third target
            val thirdRoot = FrameLayout(this)
            val third = layoutInflater.inflate(R.layout.layout_target, thirdRoot)
            val thirdTarget = Target.Builder()
                .setAnchor(binding.three)
//          .setShape(Circle(200f))
                .setShape(RoundedRectangle(600f, 400f, 20f))
                .setOverlay(third)
                .setOnTargetListener(object : OnTargetListener {
                    override fun onStarted() {
                        Toast.makeText(
                            this@MainActivity,
                            "third target is started",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onEnded() {
                        Toast.makeText(
                            this@MainActivity,
                            "third target is ended",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
                .build()

            targets.add(thirdTarget)

            // create spotlight
            val spotlight = Spotlight.Builder(this@MainActivity)
                .setTargets(targets)
                .setBackgroundColorRes(R.color.spotlightBackground)
                .setDuration(1000L)
                .setContainer(binding.container)
                .setAnimation(DecelerateInterpolator(2f))
                .setOnSpotlightListener(object : OnSpotlightListener {
                    override fun onStarted() {
                        Toast.makeText(
                            this@MainActivity,
                            "spotlight is started",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.start.isEnabled = false
                    }

                    override fun onEnded() {
                        Toast.makeText(
                            this@MainActivity,
                            "spotlight is ended",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.start.isEnabled = true
                    }
                })
                .build()

            spotlight.start()


            val nextTarget = View.OnClickListener { spotlight.next() }

            val closeSpotlight = View.OnClickListener { spotlight.finish() }

            first.findViewById<View>(R.id.close_target).setOnClickListener(nextTarget)
            second.findViewById<View>(R.id.close_target).setOnClickListener(nextTarget)
            third.findViewById<View>(R.id.close_target).setOnClickListener(nextTarget)

            first.findViewById<View>(R.id.close_spotlight).setOnClickListener(closeSpotlight)
            second.findViewById<View>(R.id.close_spotlight).setOnClickListener(closeSpotlight)
            third.findViewById<View>(R.id.close_spotlight).setOnClickListener(closeSpotlight)
        }
    }
}