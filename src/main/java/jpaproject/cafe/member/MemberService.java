package jpaproject.cafe.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jpaproject.cafe.member.dto.MemberInfoDto;
import jpaproject.cafe.member.dto.TokenDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
@Slf4j
public class MemberService {

	private final MemberRepository memberRepository;
	private final String tokenUrl;
	private final String clientId;
	private final String clientSecrets;
	private final String redirectUrl;
	private final String userInfoUrl;
	private final RestTemplate restTemplate;


	public MemberService(MemberRepository memberRepository,
		@Value("${oauth.github.request_token_url}") String tokenUrl,
		@Value("${oauth.github.client_id}") String clientId,
		@Value("${oauth.github.client_secrets}") String clientSecrets,
		@Value("${oauth.github.redirect_url}") String redirectUrl,
		@Value("${oauth.github.request_user_info_url}") String userInfoUrl,
		RestTemplate restTemplate) {
		this.memberRepository = memberRepository;
		this.tokenUrl = tokenUrl;
		this.clientId = clientId;
		this.clientSecrets = clientSecrets;
		this.redirectUrl = redirectUrl;
		this.userInfoUrl = userInfoUrl;
		this.restTemplate = restTemplate;
	}


	public void login(String code, String state, String savedState) {
		validateState(state, savedState);
		TokenDto tokenDto = obtainAccessToken(code);
		MemberInfoDto memberInfoDto = obtainMemberInfo(tokenDto);
		saveMember(memberInfoDto);
	}

	private void saveMember(MemberInfoDto memberInfoDto) {
		Member member = new Member(memberInfoDto, true);

		// 이미 존재할 경우 login 속성을 true로 업데이트, 존재하지 않으면 새로 저장
		memberRepository.findByMemberName(member.getMemberName())
			.ifPresentOrElse(findMember -> findMember.setLogin(true),
				() -> memberRepository.save(member));
	}

	private MemberInfoDto obtainMemberInfo(TokenDto tokenDto) {

		HttpHeaders requestHeader = new HttpHeaders();
		// "token " 안붙여주면 인증이 안된다
		requestHeader.set("Authorization", "token " + tokenDto.getAccessToken());

		HttpEntity<Map<String, String>> request = new HttpEntity<>(requestHeader);

		//	restTemplate.getForEntity() 는 header를 붙일 수 없기 때문에 exchange를 사용한다
		MemberInfoDto memberInfoDto = restTemplate
			.exchange(userInfoUrl, HttpMethod.GET, request, MemberInfoDto.class)
			.getBody();
		log.debug("====== {}", memberInfoDto);

		return memberInfoDto;

	}

	private TokenDto obtainAccessToken(String code) {

		Map<String, String> requestBody = new HashMap<>();

		requestBody.put("client_id", clientId);
		requestBody.put("client_secret", clientSecrets);
		requestBody.put("code", code);

		HttpHeaders requestHeader = new HttpHeaders();
		requestHeader.setAccept(List.of(MediaType.APPLICATION_JSON));

		HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, requestHeader);

		TokenDto tokenDto = restTemplate.postForObject(tokenUrl, request, TokenDto.class);

		log.debug("====== {}", tokenDto);
		// [access_token=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, scope=, token_type=bearer]

		return tokenDto;
	}

	private void validateState(String state, String savedState) {
		log.debug("====== state = {}", state);
		log.debug("====== savedState = {}", savedState);
		if (!state.equals(savedState)) {
			throw new IllegalStateException("state 불일치!! CSRF 공격인가!");
		}
	}
}
