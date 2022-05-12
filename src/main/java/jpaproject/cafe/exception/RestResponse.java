package jpaproject.cafe.exception;

import lombok.Getter;

@Getter
public class RestResponse {

    private final String errorMessage;

    private RestResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static RestResponse of(String errorMessage) {
        return new RestResponse(errorMessage);
    }
}
