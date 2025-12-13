package com.woopi.safehome.global.datasource

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import javax.sql.DataSource

/**
 * 트랜잭션의 readOnly 속성에 따라 Write/Read DataSource를 동적으로 선택하는 라우팅 DataSource
 * ThreadLocal 기반 DataSourceContextHolder를 통해 현재 스레드의 DataSource 타입을 결정
 */
class RoutingDataSource (
    private val writeDataSource: DataSource,
    private val readDataSource: DataSource
): AbstractRoutingDataSource() {

    init {
        // DataSource 타입별 매핑 설정
        val targetDataSources = mapOf<Any, Any>(
            DataSourceType.WRITE to writeDataSource,
            DataSourceType.READ to readDataSource
        )
        setTargetDataSources(targetDataSources)
        // 기본값은 Write DataSource (안전장치)
        setDefaultTargetDataSource(writeDataSource)
        // 초기화 완료
        afterPropertiesSet()
    }

    /**
     * 현재 스레드에서 사용할 DataSource 타입 결정
     * DataSourceContextHolder에 값이 없으면 기본값으로 WRITE 사용
     */
    override fun determineCurrentLookupKey(): DataSourceType {
        val key = DataSourceContextHolder.get() ?: DataSourceType.WRITE
        logger.debug("### Using DataSource: $key")
        return key
    }

}