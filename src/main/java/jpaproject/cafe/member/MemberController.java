package jpaproject.cafe.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
    public RedirectView login(RedirectAttributes attributes) {
        attributes.addAttribute("client_id", clientId);
        attributes.addAttribute("redirect_url", redirectUrl);
        attributes.addAttribute("state", "bcbc"); // Todo: 랜덤값 만들어 넣기
        return new RedirectView(loginUrl);
    }

    @GetMapping("/login/oauth")
    public void requestAccessToken(@RequestParam("code") String code,
        @RequestParam("state") String state) {
        System.out.println("=========code = " + code);
        System.out.println("=========state = " + state);
        memberService.requestAccessToken(code, state);
    }


}
