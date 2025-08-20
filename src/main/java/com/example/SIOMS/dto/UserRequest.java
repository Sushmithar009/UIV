package com.example.SIOMS.dto;

import jakarta.annotation.Nonnull;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class UserRequest {

    @NonNull()
    private String name;

    @Nonnull()
    private String password;

}
