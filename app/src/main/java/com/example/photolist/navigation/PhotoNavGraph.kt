package com.example.photolist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.photolist.ui.theme.screens.HomeScreen
import com.example.photolist.ui.theme.screens.HomeScreenDestination
import com.example.photolist.ui.theme.screens.PhotoDestination
import com.example.photolist.ui.theme.screens.PhotoDetailScreen
import com.example.photolist.ui.theme.screens.PhotosViewModel


@Composable
fun PhotoNavHost(
    navController: NavHostController,
    modifier: Modifier){
    NavHost(
        navController = navController,
        startDestination = HomeScreenDestination.route,
        modifier = modifier
    ){
        composable(route = HomeScreenDestination.route){
            val photosViewModel : PhotosViewModel = viewModel(factory = PhotosViewModel.Factory)
            HomeScreen(
                photosUiState = photosViewModel.photosUiState,
                navController = navController
            )
        }


        composable(route = PhotoDestination.routeWithArgs,
            arguments = listOf(navArgument(PhotoDestination.photoDownloadURL) {
                type = NavType.StringType
            })
        ){backStackEntry->
            val photoURL = backStackEntry.arguments?.getString(PhotoDestination.photoDownloadURL)
            if (photoURL != null) {
                PhotoDetailScreen(photoDownloadURL = photoURL, navController = navController)
            }
        }
    }
}