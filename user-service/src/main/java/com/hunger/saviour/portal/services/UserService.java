package com.hunger.saviour.portal.services;

import com.hunger.saviour.portal.dtos.SignUpRequest;
import com.hunger.saviour.portal.dtos.UserResponseDTO;
import com.hunger.saviour.portal.entities.UserEntity;
import com.hunger.saviour.portal.handlers.UsernameExist;

public interface UserService {
    UserResponseDTO createUser(SignUpRequest signUpRequest) throws UsernameExist;
}
