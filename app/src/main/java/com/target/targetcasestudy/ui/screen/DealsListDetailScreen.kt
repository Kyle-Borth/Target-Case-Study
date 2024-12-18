package com.target.targetcasestudy.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.target.targetcasestudy.R
import com.target.targetcasestudy.ui.component.TopBarState

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun DealsListDetailScreen(modifier: Modifier = Modifier, onUpdateTopBar: (TopBarState) -> Unit) {
    val context = LocalContext.current
    val navigator = rememberListDetailPaneScaffoldNavigator<Int>()

    val maxHorizontalPartitions = navigator.scaffoldDirective.maxHorizontalPartitions
    val destination = navigator.currentDestination

    val titleResId by remember(maxHorizontalPartitions, destination) {
        derivedStateOf {
            when {
                maxHorizontalPartitions == 2 && destination?.content != null -> R.string.details
                maxHorizontalPartitions == 1 && destination?.pane == ThreePaneScaffoldRole.Primary -> R.string.details
                else -> R.string.list
            }
        }
    }

    val backNav by remember(maxHorizontalPartitions, destination) {
        derivedStateOf {
            if(maxHorizontalPartitions == 1 && destination?.pane == ThreePaneScaffoldRole.Primary) {
                { navigator.navigateTo(ListDetailPaneScaffoldRole.List) }
            }
            else null
        }
    }

    LaunchedEffect(titleResId, backNav) {
        onUpdateTopBar(TopBarState(title = context.getString(titleResId), onBackSelected = backNav))
    }

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane {
                DealListScreen(onDealSelected = { navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, it) })
            }
        },
        detailPane = {
            AnimatedPane {
                navigator.currentDestination?.content?.let { dealId ->
                    DealDetailsScreen(dealId = dealId)
                } //TODO Nothing selected
            }
        },
        modifier = modifier
    )
}
