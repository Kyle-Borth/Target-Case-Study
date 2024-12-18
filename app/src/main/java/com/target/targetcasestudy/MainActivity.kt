package com.target.targetcasestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.target.targetcasestudy.ui.component.TargetTopAppBar
import com.target.targetcasestudy.ui.component.TopBarState
import com.target.targetcasestudy.ui.screen.DealsListDetailScreen
import com.target.targetcasestudy.ui.theme.TargetTheme
import com.target.targetcasestudy.ui.utility.LocalBottomSystemHeight
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TargetTheme {
                MainComposable()
            }
        }
    }

    @Composable
    private fun MainComposable() {
        val systemBarColor = MaterialTheme.colorScheme.primary
        var topBar by remember { mutableStateOf(TopBarState(title = "")) }

        LaunchedEffect(systemBarColor) {
            // Update the status bar content color to dark or light based on its background color
            WindowCompat.getInsetsController(window, window.decorView)
                .isAppearanceLightStatusBars = systemBarColor.luminance() > 0.5f

            // Update the status bar (top) background color on devices running Android 14 and below
            if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.VANILLA_ICE_CREAM) {
                @Suppress("DEPRECATION")
                window.statusBarColor = systemBarColor.toArgb()
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        TargetTopAppBar(state = topBar, modifier = Modifier.fillMaxWidth())
                        HorizontalDivider(modifier = Modifier.fillMaxWidth())
                    }
                }
            ) { innerPadding ->
                CompositionLocalProvider(LocalBottomSystemHeight provides innerPadding.calculateBottomPadding()) {
                    DealsListDetailScreen(
                        modifier = Modifier.fillMaxSize().padding(top = innerPadding.calculateTopPadding()),
                        onUpdateTopBar = { topBar = it }
                    )
                }
            }

            if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.VANILLA_ICE_CREAM) {
                Spacer( // Background for status bar at the top on Android 15 and above
                    modifier = Modifier.windowInsetsTopHeight(WindowInsets.statusBars)
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .background(color = systemBarColor)
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        TargetTheme {
            MainComposable()
        }
    }
}
