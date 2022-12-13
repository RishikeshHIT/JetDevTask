package com.Kotlintest.ApiProject.Retrofit

class Constant {

    interface TimeOut {
        companion object {
            const val SOCKET_TIME_OUT = 60
            const val CONNECTION_TIME_OUT = 60
        }
    }

    interface UrlPath {
        companion object {
            const val BASE_URL = "http://private-222d3-homework5.apiary-mock.com/api/"
        }
    }
}