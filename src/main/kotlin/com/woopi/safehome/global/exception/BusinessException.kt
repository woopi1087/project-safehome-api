package com.woopi.safehome.global.exception

class BusinessException(
    val errorCode: ErrorCode,
    override val message: String = errorCode.defaultMessage,
    val details: Any? = null
) : RuntimeException(message) {

    // 하위 호환성을 위한 프로퍼티
    val code: String get() = errorCode.code

}