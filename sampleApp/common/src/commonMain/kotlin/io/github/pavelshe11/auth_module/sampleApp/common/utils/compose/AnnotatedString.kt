package io.github.pavelshe11.auth_module.sampleApp.common.utils.compose

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import io.github.pavelshe11.auth_module.sampleApp.uikit.theme.textLinkStyles

@Composable
fun substringUrlAnnotatedString(text: String, urlSubstring: String, url: LinkAnnotation.Url): AnnotatedString {

    val privacyPolicyStartIndex = text.indexOf(urlSubstring)
    val privacyPolicyEndIndex = text.length - 1

    return buildAnnotatedString {
        append(text)
        addLink(
            url,
            privacyPolicyStartIndex,
            privacyPolicyEndIndex
        )
    }
}

@Composable
fun substringUrlAnnotatedString(text: String, urlSubstring: String, url: String): AnnotatedString {
    return substringUrlAnnotatedString(
        text,
        urlSubstring,
        LinkAnnotation.Url(
            url,
            MaterialTheme.textLinkStyles
        )
    )
}