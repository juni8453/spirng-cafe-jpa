package jpaproject.cafe.login;

import java.net.URI;
import java.net.URISyntaxException;

import jpaproject.cafe.login.dto.Token;
import jpaproject.cafe.member.dto.OauthMemberInfo;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
        loginService.saveMember(memberInfo);

        // Session 셋팅
        HttpSession session = request.getSession();
        session.setAttribute("loginMember", memberInfo);

        ResponseCookie cookie = ResponseCookie.from("username", memberInfo.getLogin())
                .path("/")
                .build();

        // 응답 헤더 셋팅 (URI 예외 처리 필요)
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI("http://localhost:8080/"));
        headers.set("Set-Cookie", cookie.toString());

        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
    }
}
