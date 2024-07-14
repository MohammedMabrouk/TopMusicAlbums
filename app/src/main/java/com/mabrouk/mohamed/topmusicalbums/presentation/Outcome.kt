package com.mabrouk.mohamed.topmusicalbums.presentation

sealed class Outcome<T> {
    data class Progress<T>(val unit: Unit = Unit) : Outcome<T>()
    data class Success<T>(val data: T) : Outcome<T>()
    data class Error<T>(val exception: Throwable) : Outcome<T>()
    data class Empty<T>(val unit: Unit = Unit) : Outcome<T>()

    companion object {
        fun <T> loading(): Outcome<T> = Progress()

        fun <T> success(data: T): Outcome<T> = Success(data)

        fun <T> error(exception: Throwable): Outcome<T> = Error(exception)

        fun <T> empty(): Outcome<T> = Empty()
    }
}