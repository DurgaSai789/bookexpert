package com.example.bookexpert.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookexpert.composable.APIListComposable
import com.example.bookexpert.composable.ImageShowingComposable
import com.example.bookexpert.composable.LoginScreenComposable
import com.example.bookexpert.composable.MainScreenComposable
import com.example.bookexpert.composable.PdfViewerFComposable


@Composable
fun NavGraphComposable(navHostController : NavHostController) {

    NavHost(navController = navHostController, startDestination = MainScreen){

        composable<LoginScreen> {
            LoginScreenComposable(navHostController)
        }

        composable<MainScreen> {
            MainScreenComposable(navHostController)
        }

        composable<PDFScreen> {
            PdfViewerFComposable()
        }

        composable<ImageShowingScreen> {
            ImageShowingComposable()
        }

        composable<APIListScreen> {
            APIListComposable()
        }
    }

}