package com.target.targetcasestudy.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.target.targetcasestudy.R
import com.target.targetcasestudy.api.DealResponse
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.ui.component.DealImage
import com.target.targetcasestudy.ui.component.DealItemPrice
import com.target.targetcasestudy.ui.component.NetworkError
import com.target.targetcasestudy.ui.component.TargetButton
import com.target.targetcasestudy.ui.theme.Black
import com.target.targetcasestudy.ui.theme.ButtonDimens
import com.target.targetcasestudy.ui.theme.PaddingNormal
import com.target.targetcasestudy.ui.theme.PaddingSmall
import com.target.targetcasestudy.ui.theme.TargetTheme
import com.target.targetcasestudy.ui.utility.LocalBottomSystemHeight
import com.target.targetcasestudy.ui.utility.TestTags
import com.target.targetcasestudy.ui.utility.getMockJson
import com.target.targetcasestudy.viewmodel.DealDetailsViewModel

@Composable
fun DealDetailsScreen(dealId: Int, viewModel: DealDetailsViewModel = hiltViewModel()) {
    val context = LocalContext.current

    LaunchedEffect(dealId) { viewModel.setDeal(dealId) }

    DealDetailsScreen(
        dealItem = viewModel.dealDetails,
        isLoading = viewModel.isLoading,
        onAddToCart = { Toast.makeText(context, "Coming Soon!", Toast.LENGTH_SHORT).show() }, //TODO
        onRetryDownload = { viewModel.setDeal(dealId) }
    )
}

@Composable
fun DealDetailsScreen(
    dealItem: DealItem?,
    isLoading: Boolean,
    onAddToCart: () -> Unit,
    onRetryDownload: () -> Unit
) {
    when {
        dealItem != null -> DealDetails(dealItem = dealItem, onAddToCart = onAddToCart)
        isLoading -> LoadingScreen()
        else -> NetworkError(modifier = Modifier.fillMaxSize(), onRetry = onRetryDownload)
    }
}

@Composable
private fun DealDetails(dealItem: DealItem, onAddToCart: () -> Unit) {
    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().verticalScroll(scrollState)) {
            ProductOverview(dealItem = dealItem, modifier = Modifier.fillMaxWidth())

            HorizontalDivider()
            Spacer(modifier = Modifier.fillMaxWidth().height(PaddingNormal))
            HorizontalDivider()

            ProductDetails(dealItem = dealItem, modifier = Modifier.fillMaxWidth().padding(bottom = PaddingSmall))

            Spacer(modifier = Modifier.fillMaxWidth().height(100.dp))
        }
        
        Surface(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
            shape = MaterialTheme.shapes.medium,
            shadowElevation = 8.dp
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().padding(bottom = LocalBottomSystemHeight.current),
                contentAlignment = Alignment.TopCenter
            ) {
                TargetButton(
                    onClick = onAddToCart,
                    modifier = Modifier.widthIn(max = ButtonDimens.CheckoutMaxWidth)
                        .fillMaxWidth()
                        .padding(PaddingNormal)
                ) {
                    Text(text = stringResource(R.string.add_to_cart))
                }
            }
        }
    }
}

@Composable
private fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.testTag(TestTags.PROGRESS_INDICATOR))
    }
}

@Composable
private fun ProductOverview(
    dealItem: DealItem,
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
) {
    Surface(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            when(windowSizeClass.windowWidthSizeClass) {
                WindowWidthSizeClass.EXPANDED -> {
                    DealImage(
                        dealItem = dealItem,
                        modifier = Modifier.align(Alignment.CenterHorizontally).size(500.dp).padding(PaddingNormal)
                    )
                }
                else -> {
                    DealImage(
                        dealItem = dealItem,
                        modifier = Modifier.fillMaxWidth().padding(PaddingNormal),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }

            Text(
                text = dealItem.title,
                modifier = Modifier.fillMaxWidth().padding(horizontal = PaddingNormal),
                color = Black, //TODO Hard-coded color
                style = MaterialTheme.typography.titleSmall
            )

            DealItemPrice(dealItem = dealItem, modifier = Modifier.fillMaxWidth().padding(PaddingNormal))
        }
    }
}

@Composable
private fun ProductDetails(dealItem: DealItem, modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.product_details),
                modifier = Modifier.padding(PaddingNormal),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = dealItem.description,
                modifier = Modifier.fillMaxWidth()
                    .padding(start = PaddingNormal, end = PaddingNormal, bottom = PaddingNormal),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DealDetailsScreenPreview() {
    val deal = LocalContext.current.getMockJson<DealResponse>("mock_products.json").deals.first()

    TargetTheme {
        DealDetailsScreen(dealItem = deal, isLoading = false, onAddToCart = {}, onRetryDownload = {})
    }
}