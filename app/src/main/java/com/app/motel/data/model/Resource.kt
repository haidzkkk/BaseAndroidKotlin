package com.app.motel.data.model

class Resource<out T> (val status: Status, val data: T?, val message: String?) {

    companion object{
        fun <T> Loading(data: T? = null,message: String? = null): Resource<T> = Resource<T>(Status.LOADING, data, message)

        fun <T> Success(data: T?, message: String? = null): Resource<T> = Resource<T>(Status.SUCCESS, data, message)

        fun <T> Error(data: T? = null, message: String?): Resource<T> = Resource<T>(Status.ERROR, data, message)
    }

    fun isLoading() = status == Status.LOADING
    fun isSuccess() = status == Status.SUCCESS
    fun isError() = status == Status.ERROR
}