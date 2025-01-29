package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.ui.theme.NewsAppTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.articlescreen.ArticleScreen

//import com.example.newsapp.ui.theme.NewsDetailsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val newsViewmodel = ViewModelProvider(this)[NewsViewmodel::class.java]
        setContent {
            NewsAppTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (
                        modifier = Modifier.padding(innerPadding).fillMaxSize()
                    ){
                        Text(text = "NEWSAPP", modifier = Modifier.align(Alignment.CenterHorizontally),
                            color = Color.Red,
                            fontSize = 25.sp,
                            fontFamily = FontFamily.Cursive
                            )
                       // HomePage(newsViewmodel)
                        NewsAppNavHost(navController, newsViewmodel)
                    }
                }
            }
        }
    }

}
@Composable
fun NewsAppNavHost(navController: NavHostController, newsViewModel: NewsViewmodel) {
    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomePage(newsViewModel, navController)
        }
        composable("articleScreen/{url}") { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url")
            if (url != null) {
                ArticleScreen(url) { navController.popBackStack() }
            }
        }
    }
}


