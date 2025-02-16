package com.merajhossen20001.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.merajhossen20001.newsapp.domain.usecases.AppEntryUseCases
import com.merajhossen20001.newsapp.presentation.navgraph.NavGraph
import com.merajhossen20001.newsapp.presentation.onboarding.OnBoardingScreen
import com.merajhossen20001.newsapp.presentation.onboarding.OnBoardingViewModel
import com.merajhossen20001.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
     val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.splashCondition
            }
        }


        setContent {

            NewsAppTheme {
                Box(Modifier.background(MaterialTheme.colorScheme.background)){

                    NavGraph(startDestination = viewModel.startDestination)
                }


            }
        }
    }
}

