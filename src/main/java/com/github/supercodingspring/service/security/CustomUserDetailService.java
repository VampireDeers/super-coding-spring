package com.github.supercodingspring.service.security;

import com.github.supercodingspring.repository.roles.Roles;
import com.github.supercodingspring.repository.userDetails.CustomUserDetails;
import com.github.supercodingspring.repository.userPrincipal.UserPrincipal;
import com.github.supercodingspring.repository.userPrincipal.UserPrincipalRepository;
import com.github.supercodingspring.repository.userPrincipal.UserPrincipalRoles;
import com.github.supercodingspring.service.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Primary
@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserPrincipalRepository userPrincipalRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserPrincipal userPrincipal = userPrincipalRepository.findByEmailFetchJoin(email)
                                                             .orElseThrow(() -> new NotFoundException("email 에 해당하는 UserPrincipal가 없습니다"));

        CustomUserDetails customUserDetails = CustomUserDetails.builder()
                                                               .userId(userPrincipal.getUser()
                                                                                    .getUserId())
                                                               .email(userPrincipal.getEmail())
                                                               .password(userPrincipal.getPassword())
                                                               .authorities(userPrincipal.getUserPrincipalRoles()
                                                                                         .stream()
                                                                                         .map(UserPrincipalRoles::getRoles)
                                                                                         .map(Roles::getName)
                                                                                         .collect(Collectors.toList()))
                                                               .build();
        return customUserDetails;
    }
}
