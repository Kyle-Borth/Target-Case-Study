package com.target.targetcasestudy.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.target.targetcasestudy.R
import com.target.targetcasestudy.api.DealResponse
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.ui.component.DealImage
import com.target.targetcasestudy.ui.component.DealItemPrice
import com.target.targetcasestudy.ui.component.NetworkError
import com.target.targetcasestudy.ui.theme.Black
import com.target.targetcasestudy.ui.theme.MediumGrey
import com.target.targetcasestudy.ui.theme.PaddingNormal
import com.target.targetcasestudy.ui.theme.PaddingSmall
import com.target.targetcasestudy.ui.theme.TargetTheme
import com.target.targetcasestudy.ui.utility.LocalBottomSystemHeight
import com.target.targetcasestudy.ui.utility.TestTags
import com.target.targetcasestudy.ui.utility.getMockJson
import com.target.targetcasestudy.viewmodel.DealListViewModel

@Composable
fun DealListScreen(viewModel: DealListViewModel = hiltViewModel(), onDealSelected: (Int) -> Unit) {
    DealListScreen(
        deals = viewModel.deals,
        isLoading = viewModel.isLoading,
        onDealSelected = onDealSelected,
        onRetryDownload = { viewModel.updateDeals() }
    )
}

@Composable
fun DealListScreen(
    deals: List<DealItem>,
    isLoading: Boolean,
    onDealSelected: (Int) -> Unit,
    onRetryDownload: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        if(deals.isNotEmpty() || isLoading) {
            DealItemList(
                deals = deals,
                isLoading = isLoading,
                modifier = Modifier.fillMaxSize(),
                onDealSelected = onDealSelected
            )
        }
        else {
            NetworkError(modifier = Modifier.fillMaxSize(), onRetry = onRetryDownload)
        }
    }
}

@Composable
private fun DealItemList(
    deals: List<DealItem>,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    onDealSelected: (Int) -> Unit
) {
    LazyColumn(modifier = modifier) {
        if(isLoading) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(PaddingNormal),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.testTag(TestTags.PROGRESS_INDICATOR))
                }
            }
        }

        items(items = deals, key = { it.id }) { item ->
            DealItemRow(item = item, modifier = Modifier.fillMaxWidth().clickable { onDealSelected(item.id) })
        }

        item {
            Spacer(modifier = Modifier.fillMaxWidth().height(LocalBottomSystemHeight.current))
        }
    }
}

//region Deal Item Row

@Composable
private fun DealItemRow(item: DealItem, modifier: Modifier = Modifier) {
    Column(modifier = modifier.testTag(TestTags.DEAL_LIST_ITEM)) {
        Row(modifier = Modifier.fillMaxWidth().padding(PaddingNormal)) {
            DealImage(dealItem = item, modifier = Modifier.weight(140f))

            Column(modifier = Modifier.weight(172f).padding(start = PaddingNormal)) {
                DealItemPrice(dealItem = item)

                Text(
                    text = item.title,
                    modifier = Modifier.padding(vertical = PaddingSmall),
                    color = Black
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = item.availability,
                        color = MaterialTheme.colorScheme.tertiary,
                        style = MaterialTheme.typography.labelSmall
                    )

                    Text(
                        text = stringResource(R.string.in_aisle, item.aisle),
                        color = MediumGrey,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }

        HorizontalDivider(modifier = Modifier.fillMaxWidth().padding(start = PaddingNormal))
    }
}

//endregion

@Preview(showBackground = true)
@Composable
fun DealListScreenPreview() {
    val deals = LocalContext.current.getMockJson<DealResponse>("mock_products.json").deals

    TargetTheme {
        DealListScreen(deals = deals, isLoading = false, onDealSelected = {}, onRetryDownload = {})
    }
}