package com.company.dto;

import com.company.enums.GeneralRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDTO {
    private String id;
    private String role;
}
