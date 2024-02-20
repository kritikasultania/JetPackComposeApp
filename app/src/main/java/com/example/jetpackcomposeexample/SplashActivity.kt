package com.example.jetpackcomposeexample

import android.annotation.SuppressLint
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.runtime.remember
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

private const val splashTime: Long = 1000

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoadUi()
        }
    }

    @Composable
    fun LoadUi() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            val scale = remember {
                androidx.compose.animation.core.Animatable(0.0f)
            }
            Image(
                painter = painterResource(id = R.drawable.banner),
                contentDescription = "",
                alignment = Alignment.Center, modifier = Modifier
                    .fillMaxSize().padding(38.dp)
                    .scale(scale.value)
            )
            LaunchedEffect(key1 = true) {
                scale.animateTo(
                    targetValue = 0.7f,
                    animationSpec = tween(1000, easing = {
                        OvershootInterpolator(4f).getInterpolation(it)
                    })
                )
                delay(splashTime)
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}