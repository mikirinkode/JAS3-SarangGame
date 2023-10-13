package com.mikirinkode.saranggame

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mikirinkode.saranggame.ui.screen.GameViewModel
import com.mikirinkode.saranggame.ui.screen.HomeScreen
import com.mikirinkode.saranggame.utils.ContentLayoutType

@Composable
fun MainApp(
    windowWidthSizeClass: WindowWidthSizeClass,
) {
    val viewModel: GameViewModel = viewModel(factory = GameViewModel.Factory)

    val contentLayoutType: ContentLayoutType

    when (windowWidthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            contentLayoutType = ContentLayoutType.LIST_ONLY
        }

        WindowWidthSizeClass.Medium -> {
            contentLayoutType = ContentLayoutType.LIST_AND_DETAIL
        }

        WindowWidthSizeClass.Expanded -> {
            contentLayoutType = ContentLayoutType.LIST_AND_DETAIL
        }

        else -> {
            contentLayoutType = ContentLayoutType.LIST_ONLY
        }
    }


    HomeScreen(
        contentLayoutType = contentLayoutType,
        viewModel = viewModel,
    )
}