package org.remo.external

class Env {
    class NATURE_REMO {
        companion object {
            val API_AUTH_KEY = System.getenv("NATURE_REMO_API_AUTH_KEY")
            val API_ENDPOINT = System.getenv("NATURE_REMO_API_ENDPOINT")
        }
    }

    class DB {
        companion object {
            val URL = System.getenv("DB_URL")
            val DRIVER_CLASS_NAME = System.getenv("DB_DRIVER_CLASS_NAME")
            val USER_NAME = System.getenv("DB_USER_NAME")
            val PASSWORD = System.getenv("DB_PASSWORD")
        }
    }
}
