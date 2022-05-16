package jpaproject.cafe.login.dto;

import lombok.Getter;

@Getter
public class LoginResponse {

    private final String tokenType;
    private final String accessToken;

    public LoginResponse(String tokenType, String accessToken) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
    }
}