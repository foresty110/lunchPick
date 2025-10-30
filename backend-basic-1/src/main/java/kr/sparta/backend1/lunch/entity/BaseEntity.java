package kr.sparta.backend1.lunch.entity;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


/* BaseEntity
 * ---------------------------
 * 엔티티의 생성일(createdAt)과 수정일(modifiedAt)을 관리하는 추상 클래스입니다.
 * 모든 엔티티에서 공통으로 사용하는 시간 필드를 정의하며
 * 엔티티의 생성 및 수정 시점을 자동으로 기록합니다.*/
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;//생성일

    @LastModifiedDate
    private LocalDateTime modifiedAt;//수정일

}