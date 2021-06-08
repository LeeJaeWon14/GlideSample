package com.example.glidesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.glidesample.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding? = null
    private val binding
        get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnChange.setOnClickListener(loadChangeListener)
        binding.btnChange.setOnLongClickListener(loadRefreshListener)

        loadGlideLocal()
    }

    private fun loadGlideLocal() {
        val list = initPhotoList()
        val randomIndex = Random.nextInt(3)

        Glide.with(this)
            .load(list[randomIndex])
            .into(binding.imgGlideImage)
    }

    private fun loadGlideURL() {
        Glide.with(this)
            .load("https://bit.ly/32dCOFa")
            .override(800, 1076)
            .error(R.drawable.photo3)
            .into(binding.imgGlideImage)
    }

    private val loadChangeListener = View.OnClickListener {
        val btn = it as Button
//        val img = binding.imgGlideImage
        when(btn.text.toString()) {
            getString(R.string.str_button_local) -> {
                btn.text = getString(R.string.str_button_URL)

                loadGlideLocal()
            }
            getString(R.string.str_button_URL) -> {
                btn.text = getString(R.string.str_button_local)

                loadGlideURL()
            }
        }
    }

    private val loadRefreshListener = View.OnLongClickListener {
        val btn = it as Button
        when(btn.text.toString()) {
            getString(R.string.str_button_local) -> {
                loadGlideLocal()
                Toast.makeText(this, "새로고침", Toast.LENGTH_SHORT).show()
            }
            getString(R.string.str_button_URL) -> {
                loadGlideURL()
                Toast.makeText(this, "새로고침", Toast.LENGTH_SHORT).show()
            }
        }

        false
    }

    private fun initPhotoList() : ArrayList<Int> {
        val list = ArrayList<Int>()
        list.add(R.drawable.photo1)
        list.add(R.drawable.photo2)
        list.add(R.drawable.photo3)

        return list
    }
}