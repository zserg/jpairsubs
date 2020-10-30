package com.zserg.jpairsubs.opensubtitles.model;

import lombok.Getter;

import java.util.Map;
import java.util.Optional;

@Getter
public class LoginResult {
    private String status;
    private Optional<String> token;

    public LoginResult(Map<String, Object> map) {
        if (map.containsKey("status")) {
            this.status = (String) map.get("status");
        }
        this.token = Optional.ofNullable((String) map.get("token"));
    }
}
