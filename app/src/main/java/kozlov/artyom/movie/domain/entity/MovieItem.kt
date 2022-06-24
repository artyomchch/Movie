package kozlov.artyom.movie.domain.entity


data class MovieItem(
    val imageUrl: String?,
    val title: String?,
    val description: String?,
    var id: Int = UNDEFINED_ID
) {


    companion object {
        const val UNDEFINED_ID = 0
    }
}


