package com.shilpakala.showcase.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shilpakala.showcase.ui.screens.home.HomeScreen
import com.shilpakala.showcase.ui.screens.gallery.GalleryScreen
import com.shilpakala.showcase.ui.screens.artwork.ArtworkDetailScreen
import com.shilpakala.showcase.ui.screens.heritage.HeritageScreen
import com.shilpakala.showcase.ui.screens.timeline.TimelineScreen
import com.shilpakala.showcase.ui.screens.upload.UploadScreen
import com.shilpakala.showcase.ui.screens.login.LoginScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("gallery") { GalleryScreen(navController) }
        composable(
            route = "artwork_detail/{artworkId}",
            arguments = listOf(navArgument("artworkId") { type = NavType.IntType })
        ) { backStackEntry ->
            val artworkId = backStackEntry.arguments?.getInt("artworkId") ?: 0
            ArtworkDetailScreen(artworkId, navController)
        }
        composable("heritage") { HeritageScreen(navController) }
        composable("wip_timeline") { TimelineScreen(navController) }
        composable("upload") { UploadScreen(navController) }
    }
}
