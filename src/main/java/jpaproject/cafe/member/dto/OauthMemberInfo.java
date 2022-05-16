package jpaproject.cafe.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class OauthMemberInfo {

    @JsonProperty("login")
    private String login;

    @JsonProperty("id")
    private String oauthId;

    @JsonProperty("name")
    private String name;

}
