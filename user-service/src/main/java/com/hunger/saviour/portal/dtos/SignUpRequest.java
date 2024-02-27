package com.hunger.saviour.portal.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import java.util.List;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequest {
    @NotBlank(message = "username is mandatory")
    @Email
    private String username;
    @NotBlank(message = "password is mandatory")
    private String password;
    @NotNull(message = "List roleNames are mandatory")
    private List<String> roles;
}
