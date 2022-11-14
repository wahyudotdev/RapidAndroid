package com.rapidcore.core.utils

@Suppress("unused")
data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val throwable: Throwable? = null,
    val showShimmer: Boolean = true
) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(
            msg: String? = null,
            throwable: Throwable? = null,
            data: T? = null,
        ): Resource<T> {
            return Resource(Status.ERROR, data, msg, throwable)
        }

        fun <T> loading(showShimmer: Boolean = true): Resource<T> {
            return Resource(Status.LOADING, null, null, showShimmer = showShimmer)
        }

        fun <T> empty(): Resource<T> {
            return Resource(Status.EMPTY, null, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    EMPTY
}