package com.example.windowinsetscontrollertest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowInsets
import androidx.core.view.WindowInsetsControllerCompat
import com.example.windowinsetscontrollertest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var hideFlag = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var controller = window?.insetsController;
        controller?.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_SWIPE;

        binding.statusBarButton.setOnClickListener {
            if (hideFlag and WindowInsets.Type.statusBars() > 0) {
                hideFlag = hideFlag and WindowInsets.Type.statusBars().inv();
                controller?.show(WindowInsets.Type.statusBars());
            } else {
                hideFlag = hideFlag or WindowInsets.Type.statusBars();
                controller?.hide(WindowInsets.Type.statusBars());
            }
        }
        binding.navigationBarButton.setOnClickListener {
            if (hideFlag and WindowInsets.Type.navigationBars() > 0) {
                hideFlag = hideFlag and WindowInsets.Type.navigationBars().inv();
                controller?.show(WindowInsets.Type.navigationBars());
            } else {
                hideFlag = hideFlag or WindowInsets.Type.navigationBars();
                controller?.hide(WindowInsets.Type.navigationBars());
            }
        }
        binding.systemBarButton.setOnClickListener {
            controller?.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE;
            if (hideFlag and WindowInsets.Type.systemBars() > 0) {
                hideFlag = hideFlag and WindowInsets.Type.systemBars().inv();
                controller?.show(WindowInsets.Type.systemBars());
            } else {
                hideFlag = hideFlag or WindowInsets.Type.systemBars();
                controller?.hide(WindowInsets.Type.systemBars());
            }
        }
        binding.mainlayout.setOnClickListener {
            var decorView = this.window.decorView;
            controller?.show(WindowInsets.Type.navigationBars());
            decorView.postDelayed({
                controller?.hide(WindowInsets.Type.navigationBars());
            }, 3000);
        }
    }
}