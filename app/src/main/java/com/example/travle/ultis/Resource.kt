package com.example.preproject.utils

class Resource<out T> (val status: Status, val data: T?, val message: String?) {

    companion object{
        fun <T> Loading(data: T?): Resource<T> = Resource<T>(Status.LOADING, data, null)

        fun <T> Success(data: T?): Resource<T> = Resource<T>(Status.SUCCESS, data, null)

        fun <T> Error(data: T?, message: String?): Resource<T> = Resource<T>(Status.ERROR, data, message)
    }
}