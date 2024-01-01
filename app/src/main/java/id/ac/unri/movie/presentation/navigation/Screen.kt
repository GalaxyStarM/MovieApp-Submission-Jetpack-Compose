package id.ac.unri.movie.presentation.navigation

sealed class Screen (val route: String){
    object Movies : Screen(route = "movies")
    object Favorite : Screen(route = "favorite")
    object Profile : Screen(route = "profile")
    object MovieDetail : Screen(route = "movies/{movieId}") {
        fun createRoute(movieId: Int?) = "movies/$movieId"
    }
}