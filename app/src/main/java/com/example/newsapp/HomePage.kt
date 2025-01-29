package com.example.newsapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.kwabenaberko.newsapilib.models.Article


@Composable
fun HomePage(newsViewmodel: NewsViewmodel,  navController: NavHostController){
    val articles by newsViewmodel.articles.observeAsState(emptyList())
    Column (
        modifier = Modifier.fillMaxSize()
    ) {

        CategoriesBar(newsViewmodel)

        LazyColumn (
            modifier = Modifier.fillMaxSize()
        ) {
            items(articles){
                    article-> ArticleItem(article) {
                    navController.navigate("articleScreen/${article.url}")
                    }
            }
        }
    }
}

@Composable
fun ArticleItem(article: Article, onClick: () -> Unit){
    Card (
        modifier = Modifier.padding(8.dp).fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ){
        Row(
            modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically
        ){
            AsyncImage(model = article.urlToImage?:"https://www.google.com/url?sa=i&url=https%3A%2F%2Fsalonlfc.com%2Fimage-not-found%2F&psig=AOvVaw2y84hwaVHsE2skrIKwaloo&ust=1728394964676000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCKDb-5Cz_IgDFQAAAAAdAAAAABAE",
                contentDescription = "Article image",
                modifier = Modifier.size(80.dp).aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Column (
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(text = article.title,
                    fontWeight = FontWeight.Bold,
                    maxLines = 3
                )
                Text(text = article.source.name, maxLines = 3, fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun CategoriesBar(newsViewmodel: NewsViewmodel){

//    val SearchQuery by remember {
//        mutableStateOf(value = " ")
//    }
//
//    var isSearchExpanded = remember {
//        mutableStateOf(value = false)
//    }

    val categoriesList = listOf(
        "GENERAL",
        "BUSINESS",
        "ENTERTAINMENT",
        "HEALTH",
        "SCIENCE",
        "SPORTS",
        "TECHNOLOGY"
    )

    Row (
        modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {

//        if(isSearchExpanded){
//
//        }
//        else {
//            IconButton(onClick = {
//                isSearchExpanded = true
//            }) {
//                Icon(imageVector = Icons.Default.Search, contentDescription = "Search icon")
//            }

        categoriesList.forEach { category ->
            Button(
                onClick = {
                    newsViewmodel.fetchNewsTopHeadlines(category)
                },
                modifier = Modifier.padding(4.dp)
            ) {
                Text(text = category)
            }
        }
    }
}

