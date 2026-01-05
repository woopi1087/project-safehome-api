package com.woopi.safehome.domain.deed.adapter.inbound.web.dto

import org.springframework.web.multipart.MultipartFile

object DeedRequest {

    data class Analyze(
        val file: MultipartFile
    )
}