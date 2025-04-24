package io.github.pavelshe11.auth_module.uikit.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.github.pavelshe11.auth_module.uikit.generated.resources.Inter_Bold
import io.github.pavelshe11.auth_module.uikit.generated.resources.Inter_Medium
import io.github.pavelshe11.auth_module.uikit.generated.resources.Inter_Regular
import io.github.pavelshe11.auth_module.uikit.generated.resources.Res
import org.jetbrains.compose.resources.Font

// Определение шрифта Inter
@Composable
fun InterFontFamily() = FontFamily(
    Font(Res.font.Inter_Regular),
    Font(Res.font.Inter_Bold),
    Font(Res.font.Inter_Medium)
)

@Composable
fun Typography(): Typography {
    return Typography(
        displayLarge = TextStyle(
            fontFamily = InterFontFamily(),
            fontWeight = FontWeight.Bold,
            fontSize = 34.sp,
            lineHeight = 42.sp,
            letterSpacing = (-0.5).sp
        ),
        displayMedium = TextStyle(
            fontFamily = InterFontFamily(),
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = (-0.25).sp
        ),
        displaySmall = TextStyle(
            fontFamily = InterFontFamily(),
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            lineHeight = 28.sp
        ),
        headlineLarge = TextStyle(
            fontFamily = InterFontFamily(),
            fontWeight = FontWeight.W600,
            fontSize = 24.sp,
            lineHeight = 32.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = InterFontFamily(),
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            lineHeight = 28.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = InterFontFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            lineHeight = 24.sp
        ),
        titleLarge = TextStyle(
            fontFamily = InterFontFamily(),
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            lineHeight = 30.sp
        ),
        titleMedium = TextStyle(
            fontFamily = InterFontFamily(),
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            lineHeight = 26.sp
        ),
        titleSmall = TextStyle(
            fontFamily = InterFontFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 22.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = InterFontFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = InterFontFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.sp
        ),
        bodySmall = TextStyle(
            fontFamily = InterFontFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 18.sp
        ),
        labelLarge = TextStyle(
            fontFamily = InterFontFamily(),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 20.sp
        ),
        labelMedium = TextStyle(
            fontFamily = InterFontFamily(),
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp
        ),
        labelSmall = TextStyle(
            fontFamily = InterFontFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            lineHeight = 14.sp
        )
    )
}
