package com.gothings.data.entities.identity;

import com.gothings.data.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(schema = "identity", name = "user_accounts")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Size(max = 15)
    @Column(nullable = false, length = 15)
    String username;
    @Column(nullable = false)
    String password;

    @Builder.Default
    @ManyToMany
    @JoinTable(
            schema = "identity", name = "user_accounts_roles",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id")
            })
    Set<UserRole> roles = new HashSet<>();

    public void addRole(UserRole userRole) {
        roles.add(userRole);
    }

    public void removeRoles(Role[] roles) {
        for (Role role : roles) {
            this.roles.removeIf(userRole -> userRole.equalsByRole(role));
        }
    }
}
