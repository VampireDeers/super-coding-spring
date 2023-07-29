package com.github.supercodingspring.repository.users;

public interface UserRepository {
    UserEntity findUserById(Integer userId);
}
