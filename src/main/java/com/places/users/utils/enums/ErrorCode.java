package com.places.users.utils.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {

    USER_NOT_FOUND("USER_NOT_FOUND");

     ErrorCode(String value) {
        this.value = value;
    }

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }








}
