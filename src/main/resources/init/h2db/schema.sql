-- 셈플 마스터 테이블
CREATE TABLE IF NOT EXISTS sample (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    name VARCHAR(100) NOT NULL COMMENT '이름',
    code VARCHAR(100) NOT NULL COMMENT '코드 (유니크)',
    description TEXT NULL COMMENT '설명',
    order_no INT NOT NULL DEFAULT 0 COMMENT '정렬 순서',
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE COMMENT '삭제 여부',
    created_id BIGINT NOT NULL COMMENT '생성자 ID',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_id BIGINT NOT NULL COMMENT '수정자 ID',
    updated_at TIMESTAMP NULL DEFAULT NULL COMMENT '수정 시간',

    UNIQUE (code)
);

CREATE INDEX idx_sample_name ON sample(name);
CREATE INDEX idx_sample_order ON sample(order_no);
CREATE INDEX idx_sample_deleted ON sample(is_deleted);

-- 셈플 상세 테이블
CREATE TABLE IF NOT EXISTS sample_detail (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    sample_id BIGINT NOT NULL COMMENT '샘플 ID',
    detail_value VARCHAR(500) NULL COMMENT '상세 값',
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE COMMENT '삭제 여부',
    created_id BIGINT NOT NULL COMMENT '생성자 ID',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_id BIGINT NOT NULL COMMENT '수정자 ID',
    updated_at TIMESTAMP NULL DEFAULT NULL COMMENT '수정 시간'
);

CREATE INDEX idx_detail_sample_id ON sample_detail(sample_id);
CREATE INDEX idx_detail_detail_value ON sample_detail(detail_value);
CREATE INDEX idx_detail_deleted ON sample_detail(is_deleted);