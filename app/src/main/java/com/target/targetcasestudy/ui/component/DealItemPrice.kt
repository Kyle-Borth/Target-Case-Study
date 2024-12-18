package com.target.targetcasestudy.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.target.targetcasestudy.R
import com.target.targetcasestudy.api.DealResponse
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.ui.theme.MediumGrey
import com.target.targetcasestudy.ui.theme.PaddingSmall
import com.target.targetcasestudy.ui.theme.TargetTheme
import com.target.targetcasestudy.ui.utility.getMockJson

@Composable
fun DealItemPrice(dealItem: DealItem, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = dealItem.salePrice.displayString,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = stringResource(R.string.reg_price, dealItem.regularPrice.displayString),
                modifier = Modifier.padding(start = PaddingSmall),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Text(
            text = dealItem.fulfillment,
            color = MediumGrey, //TODO Hard-coded color
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DealItemPricePreview() {
    val deal = LocalContext.current.getMockJson<DealResponse>("mock_products.json").deals.first()


    TargetTheme {
        DealItemPrice(dealItem = deal, modifier = Modifier.padding(PaddingSmall))
    }
}