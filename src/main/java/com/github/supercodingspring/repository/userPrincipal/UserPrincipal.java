package com.github.supercodingspring.repository.userPrincipal;

import com.github.supercodingspring.repository.users.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "user_principal")
public class UserPrincipal {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_principal_id")
    private Integer userPrincipalId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "userPrincipal")
    private Collection<UserPrincipalRoles> userPrincipalRoles;
}
