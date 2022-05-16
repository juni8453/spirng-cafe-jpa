package jpaproject.cafe.member;

import javax.servlet.http.HttpSession;
import jpaproject.cafe.member.dto.MemberInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final String loginUrl;
    private final String clientId;
    private final String redirectUrl;

    public MemberController(MemberService memberService,
		@Value("${oauth.github.login_url}") String loginUrl,
		@Value("${oauth.github.client_id}") String clientId,
		@Value("${oauth.github.redirect_url}") String redirectUrl
	) {
		this.memberService = memberService;
		this.loginUrl = loginUrl;
		this.clientId = clientId;
		this.redirectUrl = redirectUrl;
	}


	@GetMapping("/login")
	public RedirectView login(RedirectAttributes attributes, HttpSession session) {
		attributes.addAttribute("client_id", clientId);
		attributes.addAttribute("redirect_url", redirectUrl);
		attributes.addAttribute("state", "bcbc2"); // Todo: 랜덤값 만들어 넣기
		session.setAttribute("state", "bcbc2");
		session.setAttribute("set-Cookie", "bcbc2");
		return new RedirectView(loginUrl);
	}

	@GetMapping("/login/oauth")
	public ResponseEntity<MemberInfoDto> requestAccessToken(@RequestParam("code") String code,
		@RequestParam("state") String state, HttpSession session) {
		String savedState = String.valueOf(session.getAttribute("state"));
		MemberInfoDto memberInfoDto = memberService.login(code, state, savedState);
		return ResponseEntity.ok(memberInfoDto);
	}


}
