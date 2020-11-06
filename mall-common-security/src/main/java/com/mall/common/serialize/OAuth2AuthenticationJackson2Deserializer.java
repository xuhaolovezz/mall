package com.mall.common.serialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.mall.common.vo.Oauth2User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class OAuth2AuthenticationJackson2Deserializer extends StdDeserializer<OAuth2Authentication> {
    protected OAuth2AuthenticationJackson2Deserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public OAuth2Authentication deserialize(JsonParser jp,
                                            DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);
        JsonNode requestNode = readJsonNode(jsonNode, "oauth2Request");
        JsonNode userAuthenticationNode = readJsonNode(jsonNode, "userAuthentication");


        Authentication authentication = parseAuthentication(mapper, userAuthenticationNode);
        OAuth2Request request = parseOAuth2Request(mapper, requestNode);
        return new OAuth2Authentication(request, authentication);
    }

    private JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }

    private Authentication parseAuthentication(ObjectMapper mapper, JsonNode json) {
        if (mapper == null || json == null) {
            return null;
        }

        Oauth2User principal = parseOAuth2User(mapper, json.get("principal"));
        Object credentials = readValue(mapper, json.get("credentials"), Object.class);
        Set<SimpleGrantedAuthority> grantedAuthorities = parseSimpleGrantedAuthorities(mapper, json.get("authorities"));

        return new UsernamePasswordAuthenticationToken(principal, credentials, grantedAuthorities);
    }

    private Oauth2User parseOAuth2User(ObjectMapper mapper, JsonNode json) {
        if (mapper == null || json == null) {
            return null;
        }

        Integer userId = readInt(mapper, json.get("userId"));
        String username = readString(mapper, json.get("username"));
        String password = readString(mapper, json.get("password"));
        String name = readString(mapper, json.get("name"));
        String phone = readString(mapper, json.get("phone"));
        Integer status = readInt(mapper, json.get("status"));

        Set<SimpleGrantedAuthority> grantedAuthorities = parseSimpleGrantedAuthorities(mapper, json.get("authorities"));

        return Oauth2User.builder()
                .userId(userId)
                .username(username)
                .password(password)
                .name(name)
                .phone(phone)
                .status(status)
                .authorities(grantedAuthorities)
                .build();
    }

    private Set<SimpleGrantedAuthority> parseSimpleGrantedAuthorities(ObjectMapper mapper, JsonNode json) {
        Set<LinkedHashMap<String, String>> authorities = readValue(mapper, json, Set.class);
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>(0);
        if (authorities != null && !authorities.isEmpty()) {
            authorities.forEach(s -> {
                if (s != null && !s.isEmpty()) {
                    grantedAuthorities.add(new SimpleGrantedAuthority(s.get("authority")));
                }
            });
        }
        return grantedAuthorities;
    }

    private OAuth2Request parseOAuth2Request(ObjectMapper mapper, JsonNode json) {
        if (mapper == null || json == null) {
            return null;
        }
        HashMap<String, String> requestParameters = readValue(mapper, json.get("requestParameters"), HashMap.class);
        String clientId = readString(mapper, json.get("clientId"));
        String grantType = readString(mapper, json.get("grantType"));
        String redirectUri = readString(mapper, json.get("redirectUri"));
        Boolean approved = readValue(mapper, json.get("approved"), Boolean.class);

        Set<String> responseTypes = readValue(mapper, json.get("responseTypes"), Set.class);
        Set<String> scope = readValue(mapper, json.get("scope"), Set.class);
        Set<String> resourceIds = readValue(mapper, json.get("resourceIds"), Set.class);
        Map<String, Serializable> extensions = readValue(mapper, json.get("extensions"),
                new TypeReference<Map<String, Serializable>>() {
                });

        Set<SimpleGrantedAuthority> grantedAuthorities = parseSimpleGrantedAuthorities(mapper, json.get("authorities"));

        OAuth2Request request = new OAuth2Request(requestParameters, clientId,
                grantedAuthorities, approved, scope, resourceIds, redirectUri, responseTypes, extensions);
        TokenRequest tokenRequest = new TokenRequest(requestParameters, clientId, scope, grantType);
        request.refresh(tokenRequest);
        return request;
    }

    private static String readString(ObjectMapper mapper, JsonNode jsonNode) {
        return readValue(mapper, jsonNode, String.class);
    }

    private static Integer readInt(ObjectMapper mapper, JsonNode jsonNode) {
        return readValue(mapper, jsonNode, Integer.class);
    }

    private static <T> T readValue(ObjectMapper mapper, JsonNode jsonNode, Class<T> clazz) {
        if (mapper == null || jsonNode == null || clazz == null) {
            return null;
        }
        try {
            return mapper.readValue(jsonNode.traverse(), clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> T readValue(ObjectMapper mapper, JsonNode jsonNode, TypeReference<T> type) {
        if (mapper == null || jsonNode == null || type == null) {
            return null;
        }
        try {
            return mapper.readValue(jsonNode.traverse(), type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
