package jpaproject.cafe.login;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import jpaproject.cafe.login.dto.Token;
import jpaproject.cafe.member.dto.OauthMemberInfo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public RedirectView oauthRedirect(RedirectAttributes attributes) {
        return loginService.requestOauthId(attributes);
    }

    @GetMapping("/login/oauth")
    public ResponseEntity<String> githubRedirect(HttpServletRequest request,
                                                          @RequestParam String code,
                                                          @RequestParam String state) throws URISyntaxException {

        Token accessTokenInfo = loginService.getAccessToken(code, state);
        OauthMemberInfo memberInfo = loginService.getOauthMemberInfo(accessTokenInfo);

        // Session 셋팅
        HttpSession session = request.getSession();

		// sessionID를 냅다 UUID 처럼 써버리기~
		loginService.saveMember(memberInfo, session.getId());

		ResponseCookie cookie1 = ResponseCookie.from("username", memberInfo.getLogin())
			.path("/")
			.build();
		// 이 방법 말고는 React에서 세션ID를 찾을 수 있는 방법을 모르겠다.
		ResponseCookie cookie2 = ResponseCookie.from("session", session.getId())
			.path("/")
			.build();

		// 응답 헤더 셋팅 (URI 예외 처리 필요)
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(new URI("http://localhost:3000/"));
		Map<String, String> cookies = new HashMap<>();
		headers.add("Set-Cookie", cookie1.toString());
		headers.add("Set-Cookie", cookie2.toString());
		// 자꾸 쿠키 하나만 보내졌었는데 add 대신 set으로 해서 그런거였음

        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
    }
}
