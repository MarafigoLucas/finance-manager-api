package com.lucas.financemenager.model.dto;

public record UserRequest(
        String name,
        String email,
        String password
) {
}
