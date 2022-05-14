package jpaproject.cafe.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final String tokenUrl;
    private final String clientId;
    private final String clientSecrets;
    private final String redirectUrl;
    private final RestTemplate restTemplate;


    public MemberService(MemberRepository memberRepository,
        @Value("${oauth.github.request_token_url}") String tokenUrl,
        @Value("${oauth.github.client_id}") String clientId,
        @Value("${oauth.github.client_secrets}") String clientSecrets,
        @Value("${oauth.github.redirect_url}") String redirectUrl,
        RestTemplate restTemplate) {
        this.memberRepository = memberRepository;
        this.tokenUrl = tokenUrl;
        this.clientId = clientId;
        this.clientSecrets = clientSecrets;
        this.redirectUrl = redirectUrl;
        this.restTemplate = restTemplate;
    }


    public void login(String code, String state, String savedState) {
        validateState(state, savedState);

        Map<String, String> tokenMap = obtainAccessToken(code);

    }

    private Map<String, String> obtainAccessToken(String code) {

        Map<String, String> requestBody = new HashMap<>();

        requestBody.put("client_id", clientId);
        requestBody.put("client_secret", clientSecrets);
        requestBody.put("code", code);

        HttpHeaders requestHeader = new HttpHeaders();
        requestHeader.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, requestHeader);

        Map<String, String> tokenMap = restTemplate.postForObject(tokenUrl, request, HashMap.class);
        System.out.println("============map.entrySet() = " + tokenMap.entrySet());
        // map.entrySet() = [access_token=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, scope=, token_type=bearer]

        return tokenMap;
    }

    private void validateState(String state, String savedState) {
        System.out.println("state = " + state);
        System.out.println("savedState = " + savedState);
        if (!state.equals(savedState)) {
            throw new IllegalStateException("state 불일치!! CSRF 공격인가!");
        }
    }
}
