package com.places.users.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangePasswordDTO {

    @NotBlank
    private String userId;

    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;
}
