package com.gothings.data.entities;

import com.gothings.data.entities.auditing.Auditing;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

//@Builder
@SuperBuilder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "users")
public class User extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(columnDefinition = "TEXT DEFAULT ''")
    String photo;

    @Size(max = 15)
    @Column(nullable = false, length = 15)
    String username;

    @Size(max = 50)
    @Column(nullable = false, length = 50)
    String names;

    @Size(max = 250)
    @Column(length = 250, columnDefinition = "VARCHAR(250) DEFAULT ''")
    String about;

    @Size(max = 10)
    @Column(length = 10, columnDefinition = "VARCHAR(10) DEFAULT ''")
    String numberWhatsapp;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    Set<Article> articles = new HashSet<>();

}