package com.company.dto;

import com.company.enums.GeneralRole;
import lombok.Data;

@Data
public class AuthResponceDTO {
    private String id;
    private String username;
    private GeneralRole role;
    private String jwt;
}
