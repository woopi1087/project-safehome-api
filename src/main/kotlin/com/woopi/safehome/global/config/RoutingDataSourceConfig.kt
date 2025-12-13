package com.woopi.safehome.global.config

import com.woopi.safehome.global.datasource.RoutingDataSource
import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
class RoutingDataSourceConfig {

    /**
     * Write DB의 기본 연결 정보 (url, username, password 등)를 담는 Properties 객체
     * application-db.yml의 spring.datasource.write 설정을 바인딩
     */
    @Bean
    @ConfigurationProperties("spring.datasource.write")
    fun writeDataSourceProperties(): DataSourceProperties = DataSourceProperties()

    /**
     * Read DB의 기본 연결 정보를 담는 Properties 객체
     * application-db.yml의 spring.datasource.read 설정을 바인딩
     */
    @Bean
    @ConfigurationProperties("spring.datasource.read")
    fun readDataSourceProperties(): DataSourceProperties = DataSourceProperties()

    /**
     * Write용 DataSource 생성
     * 1. writeDataSourceProperties()로 url, username 등 기본 정보 가져오기
     * 2. initializeDataSourceBuilder()로 HikariDataSource 빌더 생성
     * 3. @ConfigurationProperties("spring.datasource.hikari")가 자동으로 Hikari 설정 바인딩
     */
    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    fun writeDataSource(): DataSource =
        writeDataSourceProperties().initializeDataSourceBuilder().build()

    /**
     * Read용 DataSource 생성
     * Write와 동일한 방식으로 생성하지만, readDataSourceProperties() 사용
     * 주의: write와 동일한 hikari 설정을 공유함
     */
    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    fun readDataSource(): DataSource =
        readDataSourceProperties().initializeDataSourceBuilder().build()

    /**
     * Write/Read를 동적으로 선택하는 라우팅 DataSource
     * @Primary: 기본 DataSource로 지정 (다른 DataSource 빈이 있어도 이게 우선)
     * 트랜잭션의 readOnly 속성에 따라 write/read DB를 자동 선택
     */
    @Primary
    @Bean
    fun routingDataSource(): DataSource =
        RoutingDataSource(writeDataSource(), readDataSource())

    /**
     * JPA EntityManager를 생성하는 팩토리
     * - dataSource: 위에서 만든 routingDataSource 사용
     * - packages: 엔티티 클래스들이 있는 패키지 스캔
     * - persistenceUnit: 영속성 유닛 이름 (default)
     */
    @Primary
    @Bean
    fun entityManagerFactory(
        builder: EntityManagerFactoryBuilder
    ): LocalContainerEntityManagerFactoryBean =
        builder
            .dataSource(routingDataSource())
            .packages("com.woopi.safehome.domain")
            .persistenceUnit("default")
            .build()

    /**
     * JPA 트랜잭션 관리자
     * EntityManagerFactory를 통해 트랜잭션 관리 수행
     * @Transactional 어노테이션 동작의 핵심
     */
    @Primary
    @Bean
    fun transactionManager(
        @Qualifier("entityManagerFactory") emf: EntityManagerFactory
    ): PlatformTransactionManager = JpaTransactionManager(emf)
}