//package com.example.newsapp.ui.theme
//
//import android.R.attr.title
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import coil.compose.AsyncImage
//import com.example.newsapp.NewsViewmodel
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import coil.compose.AsyncImage
//import androidx.compose.ui.layout.ContentScale
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.kwabenaberko.newsapilib.models.Article
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun NewsDetailsScreen(newsId: String?) {
//    val newsViewModel: NewsViewmodel = viewModel()
//    val article by newsViewModel.getArticleByUrl(newsId).observeAsState()
//
//    Scaffold(
//        topBar = {
//            TopAppBar(title = { Text("News Details") })
//        }
//    ) { paddingValues ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//            article?.let {
//                Text(
//                    text = it.title,
//                    fontSize = 24.sp,
//                    fontWeight = FontWeight.Bold
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                it.urlToImage?.let { imageUrl ->
//                    AsyncImage(
//                        model = imageUrl,
//                        contentDescription = "Article Image",
//                        modifier = Modifier.fillMaxWidth().height(200.dp),
//                        contentScale = ContentScale.Crop
//                    )
//                }
//                Spacer(modifier = Modifier.height(16.dp))
//                Text(
//                    text = it.content ?: "No content available",
//                    fontSize = 16.sp
//                )
//            } ?: run {
//                Text(text = "Loading article...", fontSize = 16.sp)
//            }
//        }
//    }
//}







