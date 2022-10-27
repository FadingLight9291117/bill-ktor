package cn.fadinglight.libs

import kotlinx.serialization.Serializable

@Serializable
data class RespData<T>(val code: Int, val data: T?, val message: String?)

sealed class Resp<T> {
    abstract fun json(): RespData<T>

    class Ok<T>(val data: T) : Resp<T>() {
        override fun json() = RespData<T>(code = 0, data = data, message = null)
    }

    class Error(val message: String?, val code: Int) : Resp<Unit>() {
        override fun json(): RespData<Unit> = RespData(code = code, data = null, message = message)
    }

}