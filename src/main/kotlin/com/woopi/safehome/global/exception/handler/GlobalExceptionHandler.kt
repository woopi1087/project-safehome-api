package com.woopi.safehome.global.exception.handler

import com.woopi.safehome.global.exception.BusinessException
import com.woopi.safehome.global.exception.ErrorCode
import com.woopi.safehome.global.response.ApiResponse
import jakarta.validation.ConstraintViolationException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    /**
     * BusinessException 처리
     */
    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(e: BusinessException): ResponseEntity<ApiResponse<Nothing?>> {
        return createErrorResponse(e.errorCode, e.details)
    }

    /**
     * DTO @Valid 검증 실패 (RequestBody)
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<ApiResponse<Nothing?>> {
        val errorDetails = ex.bindingResult.fieldErrors.associate { fieldError ->
            fieldError.field to (fieldError.defaultMessage ?: "잘못된 입력입니다.")
        }

        return createErrorResponse(ErrorCode.VALIDATION_FAILED, errorDetails)
    }

    /**
     * 파라미터 제약조건 실패 (RequestParam, PathVariable)
     */
    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolation(ex: ConstraintViolationException): ResponseEntity<ApiResponse<Nothing?>> {
        val errorDetails = ex.constraintViolations.associate { violation ->
            violation.propertyPath.toString() to violation.message
        }

        return createErrorResponse(ErrorCode.CONSTRAINT_VIOLATION, errorDetails)
    }

    /**
     * 일반적인 Exception 처리
     */
    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ApiResponse<Nothing?>> {
        return createErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, ex.localizedMessage)
    }

    private fun createErrorResponse(
        errorCode: ErrorCode,
        details: Any? = null
    ): ResponseEntity<ApiResponse<Nothing?>> {
        val response = ApiResponse.error(
            code = errorCode.code,
            message = errorCode.defaultMessage,
            details = details
        )
        return ResponseEntity.status(errorCode.httpStatus).body(response)
    }

}