package com.company.service;

import com.company.config.CustomUserDetails;
import com.company.dto.*;
import com.company.entity.ProfileEntity;
import com.company.exception.BadRequestException;
import com.company.repository.ProfileRepository;
import com.company.util.CurrentUser;
import com.company.util.JwtUtil;
import com.company.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;


    public LoginDTO login(AuthDTO authDTO) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword()));

        CustomUserDetails user = (CustomUserDetails) authenticate.getPrincipal();
        String id = user.getId();
        String username = user.getUsername();

        JwtDTO jwtDTO = new JwtDTO();
        jwtDTO.setId(user.getId());
        jwtDTO.setRole(user.getRole().name());

        LoginDTO responseDTO = new LoginDTO();
        responseDTO.setJwt(JwtUtil.encode(jwtDTO));
        responseDTO.setUsername(username);
        responseDTO.setRole(user.getRole());
        return responseDTO;
    }
}
