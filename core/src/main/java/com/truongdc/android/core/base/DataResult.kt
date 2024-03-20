package com.truongdc.android.core.base

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class DataResult<out R> {

    data class Success<out T>(val data: T) : DataResult<T>()
    data class Error(val exception: Exception) : DataResult<Nothing>()
    object Loading : DataResult<Nothing>()

    inline fun <M> map(block: (R) -> M): DataResult<M> {
        return when (this) {
            is Success -> Success(block(data))
            is Error -> Error(exception)
            is Loading -> Loading
        }
    }

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}