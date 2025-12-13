package com.woopi.safehome.global.datasource

/**
 * ThreadLocal을 이용해 현재 스레드의 DataSource 타입을 저장/조회
 * 주의: 반드시 사용 후 clear()를 호출해야 메모리 누수 방지 가능
 */
object DataSourceContextHolder {
    private val contextHolder = ThreadLocal<DataSourceType>()

    /** DataSource 타입 설정 */
    fun set(type: DataSourceType) {
        contextHolder.set(type)
    }

    /** 현재 스레드의 DataSource 타입 조회 */
    fun get(): DataSourceType? = contextHolder.get()

    /** ThreadLocal 값 제거 (메모리 누수 방지, 필수 호출) */
    fun clear() {
        contextHolder.remove()
    }
}