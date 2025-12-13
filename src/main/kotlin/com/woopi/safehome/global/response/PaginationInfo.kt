package com.woopi.safehome.global.response

import org.springframework.data.domain.Page

data class PaginationInfo(
    val currentPage: Int,       // 현재 페이지 (1-based)
    val totalPages: Int,        // 전체 페이지 수
    val totalElements: Long,    // 전체 데이터 개수
    val size: Int,              // 페이지당 데이터 개수
    val hasNext: Boolean,       // 다음 페이지 존재 여부
    val hasPrevious: Boolean,   // 이전 페이지 존재 여부
    val isFirst: Boolean,       // 첫 번째 페이지 여부
    val isLast: Boolean         // 마지막 페이지 여부
) {
    companion object {
        // Spring Data Page로부터 생성
        fun from(page: Page<*>): PaginationInfo {
            return PaginationInfo(
                currentPage = page.number + 1,  // 0-based -> 1-based
                totalPages = page.totalPages,
                totalElements = page.totalElements,
                size = page.size,
                hasNext = page.hasNext(),
                hasPrevious = page.hasPrevious(),
                isFirst = page.isFirst,
                isLast = page.isLast
            )
        }

        // 수동 생성
        fun of(
            currentPage: Int,
            totalElements: Long,
            size: Int
        ): PaginationInfo {
            val totalPages = if (totalElements == 0L) 0 else ((totalElements - 1) / size + 1).toInt()
            return PaginationInfo(
                currentPage = currentPage,
                totalPages = totalPages,
                totalElements = totalElements,
                size = size,
                hasNext = currentPage < totalPages,
                hasPrevious = currentPage > 1,
                isFirst = currentPage == 1,
                isLast = currentPage == totalPages || totalPages == 0
            )
        }
    }
}