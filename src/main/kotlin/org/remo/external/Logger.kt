package org.remo.external

import java.util.Date

class Logger {
    companion object {
        fun info(message: Any) {
            println("[INFO]${Date()} :$message")
        }
    }
}
