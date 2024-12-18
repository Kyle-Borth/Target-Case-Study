package com.target.targetcasestudy.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.target.targetcasestudy.R
import com.target.targetcasestudy.ui.theme.Black
import com.target.targetcasestudy.ui.theme.PaddingLarge
import com.target.targetcasestudy.ui.theme.PaddingNormal
import com.target.targetcasestudy.ui.theme.TargetTheme

//TODO Work with UX team to make this look good

@Composable
fun NetworkError(modifier: Modifier = Modifier, onRetry: () -> Unit) {
    Surface(modifier = modifier) {
        Column(
            modifier = Modifier.padding(PaddingNormal),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.network_error),
                modifier = Modifier.padding(bottom = PaddingLarge),
                style = MaterialTheme.typography.titleSmall,
                color = Black
            )

            TargetButton(onClick = onRetry) {
                Text(text = stringResource(R.string.try_again))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NetworkErrorPreview() {
    TargetTheme {
        NetworkError(onRetry = {})
    }
}