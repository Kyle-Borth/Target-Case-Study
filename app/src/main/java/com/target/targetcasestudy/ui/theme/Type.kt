package com.target.targetcasestudy.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

val Typography = Typography(
    // displayLarge is the largest display text.
    //displayLarge = TextStyle(),

    // displayMedium is the second largest display text.
    //displayMedium = TextStyle(),

    // displaySmall is the smallest display text.
    //displaySmall = TextStyle(),

    // headlineLarge is the largest headline, reserved for short, important text or numerals. For headlines, you can
    // choose an expressive font, such as a display, handwritten, or script style. These unconventional font designs
    // have details and intricacy that help attract the eye.
    //headlineLarge = TextStyle(),

    // headlineMedium is the second largest headline, reserved for short, important text or numerals. For headlines, you
    // can choose an expressive font, such as a display, handwritten, or script style. These unconventional font designs
    // have details and intricacy that help attract the eye.
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W700,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),

    // headlineSmall is the smallest headline, reserved for short, important text or numerals. For headlines, you can
    // choose an expressive font, such as a display, handwritten, or script style. These unconventional font designs
    // have details and intricacy that help attract the eye.
    //headlineSmall = TextStyle(),

    // titleLarge is the largest title, and is typically reserved for medium-emphasis text that is shorter in length.
    // Serif or sans serif typefaces work well for subtitles.
    //titleLarge = TextStyle(),

    // titleMedium is the second largest title, and is typically reserved for medium-emphasis text that is shorter in
    // length. Serif or sans serif typefaces work well for subtitles.
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W700,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),

    // titleSmall is the smallest title, and is typically reserved for medium-emphasis text that is shorter in length.
    // Serif or sans serif typefaces work well for subtitles.
    titleSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W400,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),

    // bodyLarge is the largest body, and is typically used for long-form writing as it works well for small text sizes.
    // For longer sections of text, a serif or sans serif typeface is recommended.
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W700,
        fontSize = 21.sp,
        lineHeight = 25.sp
    ),

    // bodyMedium is the second largest body, and is typically used for long-form writing as it works well for small
    // text sizes. For longer sections of text, a serif or sans serif typeface is recommended.
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        lineHeight = 20.sp
    ),

    // bodySmall is the smallest body, and is typically used for long-form writing as it works well for small text
    // sizes. For longer sections of text, a serif or sans serif typeface is recommended.
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        lineHeight = 20.5.sp
    ),

    // labelLarge text is a call to action used in different types of buttons (such as text, outlined and contained
    // buttons) and in tabs, dialogs, and cards. Button text is typically sans serif, using all caps text.
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W700,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),

    // labelMedium is one of the smallest font sizes. It is used sparingly to annotate imagery or to introduce a
    // headline.
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),

    // labelSmall is one of the smallest font sizes. It is used sparingly to annotate imagery or to introduce a
    // headline.
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        lineHeight = 16.sp
    )
)

@Preview(showBackground = true)
@Composable
private fun TypographyPreview() {
    TargetTheme {
        Column(
            modifier = Modifier.fillMaxWidth().padding(PaddingNormal),
            verticalArrangement = Arrangement.spacedBy(PaddingNormal)
        ) {
            Text(text = "Headline Medium", style = MaterialTheme.typography.headlineMedium)
            Text(text = "Title Medium", style = MaterialTheme.typography.titleMedium)
            Text(text = "Title Small", style = MaterialTheme.typography.titleSmall)
            Text(text = "Body Large", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Body Medium", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Body Small", style = MaterialTheme.typography.bodySmall)
            Text(text = "Label Large", style = MaterialTheme.typography.labelLarge)
            Text(text = "Label Medium", style = MaterialTheme.typography.labelMedium)
            Text(text = "Label Small", style = MaterialTheme.typography.labelSmall)
        }
    }
}
