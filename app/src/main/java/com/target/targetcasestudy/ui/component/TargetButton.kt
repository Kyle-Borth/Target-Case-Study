package com.target.targetcasestudy.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import com.target.targetcasestudy.ui.theme.PaddingSmall
import com.target.targetcasestudy.ui.theme.TargetTheme

@Composable
fun TargetButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.extraSmall,
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
    content: @Composable RowScope.() -> Unit
) {
    Button(onClick = onClick, modifier = modifier, shape = shape, colors = colors, content = content)
}

@Preview(showBackground = true)
@Composable
private fun TargetButtonPreview() {
    TargetTheme {
        Box {
            TargetButton(onClick = {}, modifier = Modifier.padding(PaddingSmall)) {
                Text(text = "Target Button")
            }
        }
    }
}