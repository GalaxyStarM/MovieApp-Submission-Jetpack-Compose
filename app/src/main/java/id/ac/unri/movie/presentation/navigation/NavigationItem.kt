package id.ac.unri.movie.presentation.navigation

import androidx.compose.ui.graphics.painter.Painter

data class NavigationItem(
    val title: String,
    val icon: Painter,
    val screen: Screen,
    val contentDescription: String
)
