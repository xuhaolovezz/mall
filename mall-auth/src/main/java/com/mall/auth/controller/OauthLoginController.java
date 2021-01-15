package com.mall.auth.controller;

import com.mall.common.vo.Oauth2User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
public class OauthLoginController {

    @Autowired
    private AuthorizationCodeResourceDetails details;

    @Autowired
    @Lazy
    private DefaultTokenServices tokenServices;

    @RequestMapping("/open/oauth_login")
    public Oauth2User oauthLogin(String code, HttpServletResponse response) {

        RestTemplate restTemplate = new RestTemplate();

        String url = details.getAccessTokenUri() + "?grant_type=authorization_code" +
                "&client_id=" + details.getClientId() +
                "&client_secret=" + details.getClientSecret() +
                "&code=" + code +
                "&redirect_uri=" + details.getPreEstablishedRedirectUri();

        URI uri = UriComponentsBuilder.fromUriString(url).build(true).toUri();

        DefaultOAuth2AccessToken defaultOAuth2AccessToken = restTemplate.postForObject(
                uri,
                null,
                DefaultOAuth2AccessToken.class);

        OAuth2Authentication authentication = tokenServices.loadAuthentication(defaultOAuth2AccessToken.getValue());

        Cookie cookie = new Cookie("token",defaultOAuth2AccessToken.getValue());
        cookie.setPath("/");
        response.addCookie(cookie);

        return (Oauth2User) authentication.getPrincipal();
    }

    @RequestMapping("/user/me")
    public Oauth2User currentUser() {
        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder
                .getContext().getAuthentication();
        return (Oauth2User) authentication.getPrincipal();
    }
}
