package com.sanjeevyadavit.magecart.common.components

import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun HtmlText(html: String) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                loadData(html, "text/html", "utf-8")
            }
        },
        update = { view ->
            view.loadData(html, "text/html", "utf-8")
        }
    )
}