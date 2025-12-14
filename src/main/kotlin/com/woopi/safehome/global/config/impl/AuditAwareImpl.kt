package com.woopi.safehome.global.config.impl

import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component
import java.util.Optional

@Component("auditorAware")
class AuditAwareImpl: AuditorAware<Long> {

    override fun getCurrentAuditor(): Optional<Long> {
        return Optional.of(1L)
    }

}