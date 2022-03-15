package com.aksoyhasan.path.data

sealed class Resource<T>(
        val data: T? = null,
        val errorCode: Int? = null,
        val errorMessage: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class DataError<T>(errorCode: Int, message: String? = null) : Resource<T>(null, errorCode, message)

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is DataError -> "Error[exception=$errorCode, detail=$errorMessage]"
            is Loading<T> -> "Loading"
        }
    }
}
