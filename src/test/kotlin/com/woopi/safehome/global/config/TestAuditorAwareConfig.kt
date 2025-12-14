package com.woopi.safehome.global.config

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.data.domain.AuditorAware
import java.util.Optional

@TestConfiguration
class TestAuditorAwareConfig {

    @Bean
    fun auditorAware(): AuditorAware<Long> =
        AuditorAware { Optional.of(1L) }
}