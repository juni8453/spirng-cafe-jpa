package jpaproject.cafe.login;

import java.net.URI;
import java.net.URISyntaxException;
import jpaproject.cafe.login.dto.LoginService;
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
    public ResponseEntity<String> githubRedirect(
        @RequestParam String code,
        @RequestParam String state,
        RedirectAttributes attributes) {

        Token accessTokenInfo = loginService.getAccessToken(code, state);
        OauthMemberInfo memberInfo = loginService.getOauthMemberInfo(accessTokenInfo);
        loginService.saveMember(memberInfo);

        /*-----------------------------------*/

        ResponseCookie springCookie = ResponseCookie.from("username", memberInfo.getLogin())
            .path("/")
            .build();

        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            httpHeaders.setLocation(new URI("http://localhost:3000/"));
        } catch (URISyntaxException e) {
            throw new IllegalStateException("URI가 잘못되었습니다.");
        }
        httpHeaders.set("set-cookie", springCookie.toString());

        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }


}
