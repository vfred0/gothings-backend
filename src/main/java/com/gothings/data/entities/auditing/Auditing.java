package com.gothings.data.entities.auditing;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "auditing")
@SuperBuilder
public class Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    String createdBy;

    @LastModifiedBy
    String modifiedBy;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    Instant createdAt;

    @LastModifiedDate
    Instant modifiedAt;
}
