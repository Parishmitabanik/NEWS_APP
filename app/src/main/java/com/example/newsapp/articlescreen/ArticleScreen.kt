package com.example.newsapp.articlescreen

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
//import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.material.icons.Icons

//import androidx.compose.material.icons.filled.ArrowBack
//import androidx. compose. material. icons. filled
import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx. compose. material. icons. automirrored. filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleScreen(
    url : String,
    onBackPressed : ()->Unit

){
    val context = LocalContext.current
    var isLoading by remember {
        mutableStateOf(true)
    }
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = {

                    Text("HOME",
                        modifier = Modifier.padding(bottom = 45.dp),
                        fontWeight = FontWeight.Bold
                        )},
                    navigationIcon = {
                    IconButton(onClick = onBackPressed,
                        modifier = Modifier.padding(bottom = 45.dp)
                        ) {
                        Icon(imageVector =
                        Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        }
    ) { padding->
        Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center){
            AndroidView(factory = {
                WebView(context).apply {
                    webViewClient = object : WebViewClient(){
                        override fun onPageFinished(view: WebView?, url: String?) {
                            isLoading = false
                        }
                    }

                    loadUrl(url )
                }
            })
            if(isLoading){
                CircularProgressIndicator()
            }
        }
    }
}