package com.gerardo.userlist.domain.entities.pagination

data class Page<T>(
    val previousPage: Int,
    val nextPage: Int,
    val items: List<T>
) {
    fun <R> map(transform: (T) -> R): Page<R> = Page(
        previousPage = previousPage,
        nextPage = nextPage,
        items = items.map(transform)
    )
}
