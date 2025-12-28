package com.woopi.safehome.global.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val code: String,
    val httpStatus: HttpStatus,
    val defaultMessage: String
) {
    NOT_FOUND("NOT_FOUND", HttpStatus.NOT_FOUND, "데이터를 찾을 수 없습니다."),
    VALIDATION_FAILED("VALIDATION_FAILED", HttpStatus.BAD_REQUEST, "요청 값이 유효하지 않습니다."),
    CONSTRAINT_VIOLATION("CONSTRAINT_VIOLATION", HttpStatus.BAD_REQUEST, "요청 파라미터가 유효하지 않습니다."),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.")
}