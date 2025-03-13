package com.places.users.DTOs;

import com.places.users.utils.enums.ErrorCode;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {
    private ErrorCode code;
    private String message;
}
