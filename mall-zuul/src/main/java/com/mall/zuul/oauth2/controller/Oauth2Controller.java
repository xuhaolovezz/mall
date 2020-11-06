package com.mall.zuul.oauth2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
public class Oauth2Controller {

    @Autowired
    private AuthorizationCodeResourceDetails details;

    @RequestMapping("/oauth_login")
    public DefaultOAuth2AccessToken oauthLogin(String code, HttpServletResponse response) {

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
        //SecurityContextHolder.getContext().setAuthentication();

        Cookie cookie = new Cookie("token",defaultOAuth2AccessToken.getValue());
        //cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        return defaultOAuth2AccessToken;
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

}
