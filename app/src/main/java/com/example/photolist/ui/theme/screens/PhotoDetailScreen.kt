package com.example.photolist.ui.theme.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.photolist.R
import com.example.photolist.navigation.NavigationDestination
import kotlinx.coroutines.launch
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable


object PhotoDestination : NavigationDestination {
    override val route = "singlePhoto"
    const val photoDownloadURL = "photoURL"
    val routeWithArgs = "$route/{$photoDownloadURL}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoDetailScreen(photoDownloadURL: String, navController: NavController) {
    Column(modifier = Modifier.statusBarsPadding()) {
        TopAppBar(
            title = { Text("Photo Detail") },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )
        Box(modifier = Modifier.fillMaxSize()) {
            ZoomableImageWithCoil(photoDownloadURL)
        }
    }
}

@Composable
fun ZoomableImageWithCoil(imageUrl: String) {
    val zoomState = rememberZoomState()
    val coroutineScope = rememberCoroutineScope()

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        error = painterResource(R.drawable.ic_broken_image),
        placeholder = painterResource(R.drawable.loading_img),
        modifier = Modifier
            .fillMaxSize()
            .zoomable(
                zoomState = zoomState,
                onDoubleTap = { position ->
                    coroutineScope.launch {
                        val targetScale = if (zoomState.scale > 1f) 1f else 2f
                        zoomState.changeScale(targetScale, position)
                    }
                }
            )
    )
}



