package com.target.targetcasestudy.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.target.targetcasestudy.R
import com.target.targetcasestudy.ui.theme.PaddingNormal
import com.target.targetcasestudy.ui.theme.TargetTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TargetTopAppBar(state: TopBarState, modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text(text = state.title, style = MaterialTheme.typography.headlineMedium) },
        modifier = modifier,
        navigationIcon = {
            state.onBackSelected?.let { onBack ->
                IconButton(onClick = onBack) {
                    Icon(
                        painter = painterResource(R.drawable.ic_back),
                        contentDescription = stringResource(R.string.navigate_back),
                        tint = Color.Unspecified
                    )
                }
            }
        }
    )
}

data class TopBarState(val title: String, val onBackSelected: (() -> Unit)? = null)

@Preview(showBackground = true)
@Composable
private fun TargetTopAppBarPreview() {
    TargetTheme {
        Column(
            modifier = Modifier.fillMaxWidth().padding(bottom = PaddingNormal),
            verticalArrangement = Arrangement.spacedBy(PaddingNormal)
        ) {
            TargetTopAppBar(state = TopBarState(title = "Top Bar"))

            TargetTopAppBar(state = TopBarState(title = "Top Bar", onBackSelected = {}))
        }
    }
}