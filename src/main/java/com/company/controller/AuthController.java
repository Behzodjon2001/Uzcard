package com.company.controller;

import com.company.dto.AuthDTO;
import com.company.dto.AuthResponceDTO;
import com.company.dto.LoginDTO;
import com.company.dto.ProfileDTO;
import com.company.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Authorization")
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @ApiOperation(value = "Login", notes="Method for Authziration", nickname = "Some Nick Name")
    @PostMapping("/admBankPay/login")
    public ResponseEntity<LoginDTO> login(@RequestBody AuthDTO dto) {
        log.info("Request for login {}" , dto);
        LoginDTO generalDTO = authService.login(dto);
        return ResponseEntity.ok(generalDTO);
    }
}
