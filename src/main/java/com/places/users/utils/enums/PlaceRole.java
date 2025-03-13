package com.places.users.utils.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum PlaceRole {

    ADMIN("admin"),
    OWNER("owner"),
    EMPLOYEE("employee");

    PlaceRole(String value) {
        this.value = value;
    }

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }

    public static PlaceRole fromValue(String value) {
        return Arrays.stream(PlaceRole.values())
                .filter(role -> role.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No se encontró PlaceRole para el valor: " + value));
    }
}
