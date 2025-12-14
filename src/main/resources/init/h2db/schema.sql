-- 셈플 마스터 테이블
CREATE TABLE IF NOT EXISTS samples (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    name VARCHAR(100) NOT NULL COMMENT '이름',
    code VARCHAR(100) NOT NULL COMMENT '코드 (유니크)',
    description TEXT NULL COMMENT '설명',
    order_no INT NOT NULL DEFAULT 0 COMMENT '정렬 순서',
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE COMMENT '삭제 여부',
    created_id BIGINT NOT NULL COMMENT '생성자 ID',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_id BIGINT NULL COMMENT '수정자 ID',
    updated_at TIMESTAMP NULL DEFAULT NULL COMMENT '수정 시간',

    UNIQUE (code)
);

CREATE INDEX IF NOT EXISTS idx_samples_name ON samples(name);
CREATE INDEX IF NOT EXISTS idx_samples_order ON samples(order_no);
CREATE INDEX IF NOT EXISTS idx_samples_deleted ON samples(is_deleted);

-- 셈플 상세 테이블
CREATE TABLE IF NOT EXISTS sample_details (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    sample_id BIGINT NOT NULL COMMENT '샘플 ID',
    detail_value VARCHAR(100) NULL COMMENT '상세 값',
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE COMMENT '삭제 여부',
    created_id BIGINT NOT NULL COMMENT '생성자 ID',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_id BIGINT NULL COMMENT '수정자 ID',
    updated_at TIMESTAMP NULL DEFAULT NULL COMMENT '수정 시간'
);

CREATE INDEX IF NOT EXISTS idx_sample_details_sample_id ON sample_details(sample_id);
CREATE INDEX IF NOT EXISTS idx_sample_details_detail_value ON sample_details(detail_value);
CREATE INDEX IF NOT EXISTS idx_sample_details_deleted ON sample_details(is_deleted);