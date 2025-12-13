package com.woopi.safehome.global.aop

import com.woopi.safehome.global.datasource.DataSourceContextHolder
import com.woopi.safehome.global.datasource.DataSourceType
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Aspect
@Component
class DataSourceTransactionInterceptor {

    @Around("@annotation(transactional)")
    fun setDataSource(joinPoint: ProceedingJoinPoint, transactional: Transactional): Any? {
        try {
            // readOnly면 READ, 아니면 WRITE
            val type = if (transactional.readOnly) DataSourceType.READ else DataSourceType.WRITE
            DataSourceContextHolder.set(type)
            return joinPoint.proceed()
        } finally {
            DataSourceContextHolder.clear() // 항상 해제
        }
    }
}