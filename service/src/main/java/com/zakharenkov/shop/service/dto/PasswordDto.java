package com.zakharenkov.shop.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordDto {

    private String oldPassword;
    private String newPassword;
    private String newPasswordRepeat;
}
