package com.lucas.financemenager.model.dto;

public record UserUpdateRequest(
        String name,
        String email,
        String password
) {}
