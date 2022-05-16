package jpaproject.cafe.login;

import jpaproject.cafe.login.dto.LoginService;
import jpaproject.cafe.login.dto.Token;

import jpaproject.cafe.member.dto.OauthMemberInfo;
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
    public ResponseEntity<OauthMemberInfo> githubRedirect(
            @RequestParam String code,
            @RequestParam String state) {

        Token accessTokenInfo = loginService.getAccessToken(code, state);
        OauthMemberInfo memberInfo = loginService.getOauthMemberInfo(accessTokenInfo);
        loginService.saveMember(memberInfo);
        return ResponseEntity.ok(memberInfo);
    }


}
