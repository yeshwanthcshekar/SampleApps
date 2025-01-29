package com.example.photolist.ui.theme.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.photolist.R
import com.example.photolist.navigation.NavigationDestination
import com.example.photolist.network.Photo


object HomeScreenDestination : NavigationDestination{
    override val route = "home"
}



@Composable
fun HomeScreen(
    photosUiState: PhotosUiState,
    modifier: Modifier = Modifier,
    navController: NavController
){
    when(photosUiState){
        is PhotosUiState.Loading -> LoadingScreen()
        is PhotosUiState.Error -> ErrorScreen()
        is PhotosUiState.Success -> PhotosGridScreen(photosUiState.photos,
            modifier = modifier,
            navController = navController)
    }
}



@Composable
fun PhotosGridScreen(photos: List<Photo>,
                     modifier: Modifier = Modifier, navController: NavController){
    LazyVerticalGrid(columns = GridCells.Fixed(3),
        modifier = modifier) {
        items(items = photos, key = { photo -> photo.id }){
            photo -> PhotoCard(photo,
                modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f),
                navController = navController)
        }
    }
}


@Composable
fun PhotoCard(photo: Photo, modifier: Modifier = Modifier, navController : NavController){
    Card(modifier = modifier.clickable {
        val encodedUrl = Uri.encode(photo.download_url)
        navController.navigate("${PhotoDestination.route}/$encodedUrl")
    },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)) {
        AsyncImage(model = ImageRequest.Builder(context = LocalContext.current)
            .data(photo.download_url)
            .crossfade(true)
            .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            modifier = Modifier)
    }
}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier){
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = "Loading")
}

@Composable
fun ErrorScreen() {
    Column {
        Image(
            painter = painterResource(R.drawable.ic_connection_error),
            contentDescription = "Error Loading Image"
        )
        Text(text = "Failed to Load", modifier = Modifier.padding(16.dp))
    }
}
