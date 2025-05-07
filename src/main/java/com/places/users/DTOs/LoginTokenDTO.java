package com.places.users.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginTokenDTO {

    @NotBlank(message = "token is required")
    private String token;
}
