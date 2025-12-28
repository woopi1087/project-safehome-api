package com.woopi.safehome.global.response

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

// ============================================================
// Jackson 라이브러리에게 "이 Sealed Class를 JSON으로 변환할 때
// 어떤 타입인지 구분하는 정보를 추가해줘"라고 알려주는 애노테이션
// ============================================================
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,              // "이름"으로 타입 구분
    include = JsonTypeInfo.As.PROPERTY,      // JSON 객체의 "속성"으로 포함
    property = "type"                        // 속성 이름은 "type"
)
// 결과: JSON에 "type": "success" 또는 "type": "error" 필드가 추가됨

// ============================================================
// "어떤 하위 타입들이 있는지" Jackson에게 알려주는 애노테이션
// ============================================================
@JsonSubTypes(
    JsonSubTypes.Type(value = ApiResponse.Success::class, name = "success"),
    // Success 클래스는 JSON에서 "type": "success"로 표현됨

    JsonSubTypes.Type(value = ApiResponse.Error::class, name = "error")
    // Error 클래스는 JSON에서 "type": "error"로 표현됨
)

// ============================================================
// sealed class: "봉인된" 클래스
// - 이 파일 안에서만 상속 가능 (외부에서 새로운 타입 추가 불가)
// - Success와 Error 딱 2가지만 존재한다고 컴파일러가 보장
// - when 표현식에서 모든 케이스를 강제함
// ============================================================
sealed class ApiResponse<out T> {
    // <out T>: 공변성(covariance)
    // ApiResponse<User>를 ApiResponse<Any>로 취급 가능
    // "Success는 T 타입 데이터를 생산만 하고 소비하지 않음"

    // ============================================================
    // abstract: Success와 Error 둘 다 message를 가져야 함
    // 공통 속성을 추상 프로퍼티로 정의
    // ============================================================
    abstract val message: String

    // ============================================================
    // Success: 성공 응답을 나타내는 데이터 클래스
    // ============================================================
    data class Success<T>(
        val data: T,                                    // 실제 응답 데이터 (제네릭)
        override val message: String = "Success",       // 부모의 message 구현 (기본값)
        val pagination: PaginationInfo? = null          // 페이징 정보 (선택적)
    ) : ApiResponse<T>()  // ApiResponse<T>를 상속
    // 생성 예시: ApiResponse.Success(data = user, message = "조회 성공")

    // ============================================================
    // Error: 에러 응답을 나타내는 데이터 클래스
    // ============================================================
    data class Error(
        val code: String,                               // 에러 코드 (예: "USER_NOT_FOUND")
        override val message: String,                   // 에러 메시지 (필수)
        val details: Any? = null          // 추가 상세 정보 (선택적)
    ) : ApiResponse<Nothing>()  // Nothing: "데이터 없음"을 타입으로 표현
    // Nothing: Kotlin의 특수 타입, 모든 타입의 하위 타입
    // "이 에러 응답은 data 필드를 가지지 않는다"를 타입으로 표현
    // 생성 예시: ApiResponse.Error(code = "404", message = "찾을 수 없음")

    companion object {
        // 팩토리 메서드 추가
        fun <T> success(
            data: T,
            message: String = "Success",
            pagination: PaginationInfo? = null
        ): Success<T> = Success(data, message, pagination)

        fun error(
            code: String,
            message: String,
            details: Any? = null
        ): Error = Error(code, message, details)
    }

}