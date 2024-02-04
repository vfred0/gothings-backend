package com.gothings.data.entities;

import com.gothings.data.entities.auditing.Auditing;
import com.gothings.data.enums.Category;
import com.gothings.data.enums.Gender;
import com.gothings.data.enums.State;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
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
@Table(name = "articles")
public class Article extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Size(max = 60) @NotNull
    @Column(nullable = false, length = 60)
    String title;

    @Size(max = 250) @NotNull
    @Column(nullable = false, length = 250, columnDefinition = "VARCHAR(250) DEFAULT ''")
    String description;

    @Column(nullable = false, insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    LocalDateTime date;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    Category category = Category.TEXT_BOOKS_EDUCATIONAL_MATERIAL;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    State state = State.NEW;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    Gender gender = Gender.UNISEX;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    User user;

    @ElementCollection
    @CollectionTable(name = "article_images", joinColumns = @JoinColumn(name = "article_id"))
    Set<String> images;

    @Builder.Default
    @Column(nullable = false, insertable = false, columnDefinition = "INT DEFAULT 0")
    Integer likes = 0;

    @Builder.Default
    @Column(nullable = false, insertable = false, columnDefinition = "INT DEFAULT 0")
    Integer dislikes = 0;
}