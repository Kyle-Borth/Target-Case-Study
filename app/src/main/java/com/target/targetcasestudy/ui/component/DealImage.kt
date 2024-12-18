package com.target.targetcasestudy.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.target.targetcasestudy.R
import com.target.targetcasestudy.api.DealResponse
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.ui.theme.PaddingNormal
import com.target.targetcasestudy.ui.theme.TargetTheme
import com.target.targetcasestudy.ui.utility.getMockJson

@Composable
fun DealImage(dealItem: DealItem, modifier: Modifier = Modifier, contentScale: ContentScale = ContentScale.Fit) {
    AsyncImage(
        model = dealItem.imageUrl,
        contentDescription = null,
        modifier = modifier,
        placeholder = painterResource(R.drawable.ic_launcher_foreground), //TODO Better placeholder
        contentScale = contentScale
    )
}

@Preview(showBackground = true)
@Composable
private fun DealItemPreview() {
    val deal = LocalContext.current.getMockJson<DealResponse>("mock_products.json").deals.first()

    TargetTheme {
        Column(modifier = Modifier.padding(PaddingNormal)) {
            DealImage(dealItem = deal, modifier = Modifier.fillMaxWidth())
        }
    }
}